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


package com.io7m.gatwick.controller.api;

import com.io7m.gatwick.device.api.GWDeviceConfiguration;
import com.io7m.gatwick.device.api.GWDeviceFactoryType;
import com.io7m.taskrecorder.core.TRTask;
import com.io7m.taskrecorder.core.TRTaskRecorderType;

import java.util.List;
import java.util.function.Predicate;

/**
 * A controller factory.
 */

public interface GWControllerFactoryType
{
  /**
   * Open a controller.
   *
   * @param deviceFactory       The device factory
   * @param deviceConfiguration The device configuration
   *
   * @return A controller
   *
   * @throws GWControllerException On errors
   */

  GWControllerType openController(
    GWDeviceFactoryType deviceFactory,
    GWDeviceConfiguration deviceConfiguration)
    throws GWControllerException;

  /**
   * Detect devices that appear to be GT-1000 devices.
   *
   * @param recorder A task recorder
   * @param devices  The device factories
   *
   * @return A list of probable GT-1000 devices
   */

  TRTask<List<GWControllerDetectedDevice>> detectDevicesWith(
    TRTaskRecorderType<?> recorder,
    List<GWDeviceFactoryType> devices);

  /**
   * Detect devices that appear to be GT-1000 devices.
   *
   * @param recorder            A task recorder
   * @param deviceFactoryFilter The predicate used to filter device factories
   *
   * @return A list of probable GT-1000 devices
   */

  TRTask<List<GWControllerDetectedDevice>> detectDevices(
    TRTaskRecorderType<?> recorder,
    Predicate<GWDeviceFactoryType> deviceFactoryFilter);
}
