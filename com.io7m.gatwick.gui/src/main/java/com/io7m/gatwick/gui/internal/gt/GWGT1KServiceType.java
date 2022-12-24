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

import com.io7m.gatwick.controller.api.GWControllerConfiguration;
import com.io7m.gatwick.device.api.GWDeviceMIDIDescription;
import com.io7m.repetoir.core.RPServiceType;
import com.io7m.taskrecorder.core.TRTask;
import javafx.beans.property.ReadOnlyProperty;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
   * @param configuration The configuration
   *
   * @return The operation in progress
   */

  CompletableFuture<TRTask<?>> open(
    GWControllerConfiguration configuration);

  /**
   * Detect devices.
   *
   * @return The operation in progress
   */

  CompletableFuture<TRTask<List<GWDeviceMIDIDescription>>> detectDevices();
}