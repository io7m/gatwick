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


package com.io7m.gatwick.gui.internal.gt;

import com.io7m.gatwick.controller.api.GWControllerDetectedDevice;
import com.io7m.gatwick.device.api.GWDeviceConfiguration;
import com.io7m.gatwick.device.api.GWDeviceFactoryType;
import com.io7m.repetoir.core.RPServiceType;
import com.io7m.taskrecorder.core.TRTask;
import javafx.beans.property.ReadOnlyProperty;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

/**
 * The GT-1000 service.
 */

public interface GWGT1KServiceType extends RPServiceType, AutoCloseable
{
  /**
   * @return The GT-1000 status.
   */

  ReadOnlyProperty<GWGT1KServiceStatusType> status();

  /**
   * Try to open a device.
   *
   * @param deviceFactory The device factory
   * @param configuration The configuration
   *
   * @return The operation in progress
   */

  CompletableFuture<TRTask<?>> open(
    GWDeviceFactoryType deviceFactory,
    GWDeviceConfiguration configuration);

  /**
   * Detect devices.
   *
   * @param deviceFactoryFilter The device factory filter predicate
   *
   * @return The operation in progress
   */

  CompletableFuture<TRTask<List<GWControllerDetectedDevice>>> detectDevices(
    Predicate<GWDeviceFactoryType> deviceFactoryFilter);

  /**
   * Execute a function on the device thread.
   *
   * @param longRunning The long running spec
   * @param runnable The function
   *
   * @return The operation in progress
   */

  CompletableFuture<?> executeOnDevice(
    GWGTK1LongRunning longRunning,
    GWGT1KRunnableType runnable);

  /**
   * @return {@code true} if a device is open
   */

  boolean isOpen();

  /**
   * Close the open device, if one is open.
   *
   * @return The operation in progress
   */

  CompletableFuture<?> closeDevice();
}
