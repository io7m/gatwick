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


package com.io7m.gatwick.gui.internal.preset;

import com.io7m.gatwick.controller.api.GWControllerType;
import com.io7m.gatwick.controller.api.GWPatchEffectBlockFXType;
import com.io7m.repetoir.core.RPServiceDirectoryType;

/**
 * A panel for the FX1 block.
 */

public final class GWEffectBlockPanelFX1 extends GWEffectBlockPanelFX
{
  /**
   * A panel for the FX1 block.
   *
   * @param services The service directory
   */

  public GWEffectBlockPanelFX1(
    final RPServiceDirectoryType services)
  {
    super(services);
  }

  @Override
  protected GWPatchEffectBlockFXType fx(
    final GWControllerType device)
  {
    return device.patchCurrent().fx1();
  }
}
