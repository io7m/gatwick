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


package com.io7m.gatwick.gui.internal.errors;

import com.io7m.gatwick.gui.internal.GWCSS;
import com.io7m.gatwick.gui.internal.GWStrings;
import com.io7m.repetoir.core.RPServiceDirectoryType;
import com.io7m.repetoir.core.RPServiceType;
import com.io7m.taskrecorder.core.TRTask;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Objects;

import static javafx.stage.Modality.APPLICATION_MODAL;

/**
 * A service for creating error dialogs.
 */

public final class GWErrorDialogs implements RPServiceType
{
  private final GWStrings strings;
  private final RPServiceDirectoryType services;

  /**
   * A service for creating error dialogs.
   *
   * @param inServices The service directory
   */

  public GWErrorDialogs(
    final RPServiceDirectoryType inServices)
  {
    this.services =
      Objects.requireNonNull(inServices, "inServices");
    this.strings =
      inServices.requireService(GWStrings.class);
  }

  /**
   * Open an error dialog assuming that the given task failed.
   *
   * @param task The failed task
   */

  public void open(
    final TRTask<?> task)
  {
    try {
      final var stage = new Stage();

      final var layout =
        GWErrorDialogs.class.getResource(
          "/com/io7m/gatwick/gui/internal/error.fxml");

      Objects.requireNonNull(layout, "layout");

      final var loader =
        new FXMLLoader(layout, this.strings.resources());

      loader.setControllerFactory(param -> {
        return new GWErrorController(this.services, task, stage);
      });

      final Pane pane = loader.load();
      GWCSS.setCSS(pane);

      stage.initModality(APPLICATION_MODAL);
      stage.setTitle(this.strings.format("error.header"));
      stage.setWidth(640.0);
      stage.setHeight(480.0);
      stage.setScene(new Scene(pane));
      stage.show();
    } catch (final IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public String toString()
  {
    return String.format(
      "[GWErrorDialogs 0x%08x]",
      Integer.valueOf(this.hashCode())
    );
  }

  @Override
  public String description()
  {
    return "Error dialog service";
  }
}
