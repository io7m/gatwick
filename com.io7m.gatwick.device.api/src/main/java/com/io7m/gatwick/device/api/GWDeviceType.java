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


package com.io7m.gatwick.device.api;

import com.io7m.jattribute.core.AttributeReadableType;

import java.time.Duration;

/**
 * A device.
 */

public interface GWDeviceType extends AutoCloseable
{
  /**
   * @return A description of the device
   */

  GWDeviceDescription description();

  /**
   * @return The round trip time of the most recent command
   */

  AttributeReadableType<Duration> commandRoundTripTime();

  /**
   * Send a command to the device.
   *
   * @param command The command
   * @param <R>     The precise type of response
   *
   * @return The response
   *
   * @throws GWDeviceException On errors
   */

  <R extends GWDeviceResponseType>
  R sendCommand(
    GWDeviceCommandType<R> command)
    throws GWDeviceException, InterruptedException;

  @Override
  void close()
    throws GWDeviceException;
}
