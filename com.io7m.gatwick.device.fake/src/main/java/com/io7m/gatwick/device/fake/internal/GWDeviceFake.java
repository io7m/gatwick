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

package com.io7m.gatwick.device.fake.internal;

import com.io7m.gatwick.device.api.GWDeviceCommandType;
import com.io7m.gatwick.device.api.GWDeviceDescription;
import com.io7m.gatwick.device.api.GWDeviceException;
import com.io7m.gatwick.device.api.GWDeviceResponseType;
import com.io7m.gatwick.device.api.GWDeviceType;

import java.util.Objects;

import static com.io7m.gatwick.device.api.GWDeviceStandardErrorCodes.DEVICE_MIDI_SYSTEM_ERROR;

/**
 * A fake device.
 */

public final class GWDeviceFake implements GWDeviceType
{
  private final long id;
  private final GWDeviceDescription description;

  /**
   * A fake device.
   *
   * @param inId          The device ID
   * @param inDescription The device description
   */

  public GWDeviceFake(
    final long inId,
    final GWDeviceDescription inDescription)
  {
    this.id = inId;
    this.description =
      Objects.requireNonNull(inDescription, "newDescription");
  }

  @Override
  public GWDeviceDescription description()
  {
    return this.description;
  }

  @Override
  public <R extends GWDeviceResponseType> R sendCommand(
    final GWDeviceCommandType<R> command)
    throws GWDeviceException
  {
    throw new GWDeviceException(DEVICE_MIDI_SYSTEM_ERROR, "MIDI system error");
  }

  @Override
  public String toString()
  {
    return "[GWDeviceFake 0x%016x]".formatted(this.id);
  }

  @Override
  public void close()
  {

  }

  /**
   * @return The device ID
   */

  public long id()
  {
    return this.id;
  }
}