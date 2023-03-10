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


package com.io7m.gatwick.device.javamidi;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

/**
 * A backend that talks to Java MIDI.
 */

public final class GWDevicesJavaMIDIDevices
  implements GWDevicesJavaMIDIDevicesType
{
  /**
   * A backend that talks to Java MIDI.
   */

  public GWDevicesJavaMIDIDevices()
  {

  }

  @Override
  public MidiDevice.Info[] getMidiDeviceInfo()
  {
    return MidiSystem.getMidiDeviceInfo();
  }

  @Override
  public MidiDevice getMidiDevice(
    final MidiDevice.Info info)
    throws MidiUnavailableException
  {
    return MidiSystem.getMidiDevice(info);
  }
}
