/*
 * Copyright © 2022 Mark Raynsford <code@io7m.com> https://www.io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.gatwick.controller.main;

import com.io7m.gatwick.controller.api.GWControllerDetectedDevice;
import com.io7m.gatwick.controller.api.GWControllerException;
import com.io7m.gatwick.controller.api.GWControllerFactoryType;
import com.io7m.gatwick.controller.api.GWControllerType;
import com.io7m.gatwick.controller.main.internal.GWController;
import com.io7m.gatwick.device.api.GWDeviceConfiguration;
import com.io7m.gatwick.device.api.GWDeviceFactoryType;
import com.io7m.gatwick.device.api.GWDeviceMIDIDescription;
import com.io7m.taskrecorder.core.TRTask;
import com.io7m.taskrecorder.core.TRTaskRecorderType;
import com.io7m.taskrecorder.core.TRTaskSucceeded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.function.Predicate;

import static com.io7m.gatwick.controller.api.GWControllerStandardErrorCodes.DEVICE_NO_SUITABLE_FACTORIES;

/**
 * A factory of controllers.
 */

public final class GWControllers implements GWControllerFactoryType
{
  private static final Logger LOG =
    LoggerFactory.getLogger(GWControllers.class);

  /**
   * Create a factory of controllers.
   */

  public GWControllers()
  {

  }

  private static GWDeviceFactoryType findDeviceFactory(
    final Predicate<GWDeviceFactoryType> filter)
    throws GWControllerException
  {
    final var loader =
      ServiceLoader.load(GWDeviceFactoryType.class);
    final var iterator =
      loader.iterator();

    while (iterator.hasNext()) {
      final var devices = iterator.next();
      if (filter.test(devices)) {
        return devices;
      }
    }

    throw new GWControllerException(
      DEVICE_NO_SUITABLE_FACTORIES,
      "No suitable services available of type %s"
        .formatted(GWDeviceFactoryType.class)
    );
  }

  @Override
  public GWControllerType openController(
    final GWDeviceFactoryType deviceFactory,
    final GWDeviceConfiguration deviceConfiguration)
    throws GWControllerException
  {
    return GWController.open(deviceFactory, deviceConfiguration);
  }

  @Override
  public TRTask<List<GWControllerDetectedDevice>> detectDevicesWith(
    final TRTaskRecorderType<?> recorder,
    final List<GWDeviceFactoryType> devices)
  {
    final var allDevices =
      new ArrayList<GWControllerDetectedDevice>(8);

    try (var subRec =
           recorder.<List<GWControllerDetectedDevice>>beginSubtask(
      "Detecting devices...")) {

      for (final var deviceFactory : devices) {
        final var result =
          deviceFactory.detectDevices(subRec);
        final var resolution =
          result.resolution();

        if (resolution instanceof TRTaskSucceeded<List<GWDeviceMIDIDescription>> success) {
          final var detected = success.result();
          for (final var device : detected) {
            allDevices.add(new GWControllerDetectedDevice(deviceFactory, device));
          }
        }
      }

      subRec.setTaskSucceeded("", List.copyOf(allDevices));
      return subRec.toTask();
    }
  }

  @Override
  public TRTask<List<GWControllerDetectedDevice>> detectDevices(
    final TRTaskRecorderType<?> recorder,
    final Predicate<GWDeviceFactoryType> deviceFactoryFilter)
  {
    final var loader =
      ServiceLoader.load(GWDeviceFactoryType.class);

    final var factories =
      loader.stream()
        .map(ServiceLoader.Provider::get)
        .filter(deviceFactoryFilter)
        .toList();

    return this.detectDevicesWith(recorder, factories);
  }
}
