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


package com.io7m.gatwick.iovar;

/**
 * A duration in milliseconds between 0 and 100.
 *
 * @param value The millisecond value
 */

public record GWIORate119Milliseconds(
  int value)
  implements GWIORate119Type
{
  /**
   * A duration in milliseconds between 0 and 100.
   *
   * @param value The millisecond value
   */

  public GWIORate119Milliseconds(
    final int value)
  {
    this.value = Math.max(0, Math.min(value, 100));
  }

  @Override
  public int toInt()
  {
    return this.value;
  }

  @Override
  public GWIORate119Type next()
  {
    if (this.value == 100) {
      return GWIORate119Note.info().first();
    }
    return new GWIORate119Milliseconds(this.value + 1);
  }

  @Override
  public GWIORate119Type previous()
  {
    if (this.value == 0) {
      return this;
    }
    return new GWIORate119Milliseconds(this.value - 1);
  }
}
