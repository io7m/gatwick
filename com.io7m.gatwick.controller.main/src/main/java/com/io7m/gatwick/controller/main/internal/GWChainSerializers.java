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


package com.io7m.gatwick.controller.main.internal;

import com.io7m.gatwick.controller.api.GWChain;
import com.io7m.gatwick.controller.api.GWChainElementValue;
import com.io7m.gatwick.iovar.GWIOVariableDeserializeType;
import com.io7m.gatwick.iovar.GWIOVariableSerializeType;

import java.util.ArrayList;

final class GWChainSerializers
{
  private GWChainSerializers()
  {

  }

  public static GWIOVariableSerializeType<GWChain> serializer()
  {
    return (buffer, value) -> {
      final var elements = value.elements();
      for (int index = 0; index < elements.size(); ++index) {
        final var e = elements.get(index);
        buffer.put(index, (byte) GWChainElementValue.info().toInt(e));
      }
    };
  }

  public static GWIOVariableDeserializeType<GWChain> deserializer()
  {
    return buffer -> {
      final var elements = new ArrayList<GWChainElementValue>(49);
      for (int index = 0; index < 49; ++index) {
        elements.add(
          GWChainElementValue.info().fromInt((int) buffer.get(index) & 0xff)
        );
      }
      return GWChain.of(elements);
    };
  }
}
