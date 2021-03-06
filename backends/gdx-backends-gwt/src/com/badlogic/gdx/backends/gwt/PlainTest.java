package com.badlogic.gdx.backends.gwt;

import gwt.g2d.client.util.FpsTimer;
import gwt.g3d.client.Surface3D;
import gwt.g3d.client.gl2.GL2;
import gwt.g3d.client.gl2.WebGLContextAttributes;
import gwt.g3d.client.gl2.enums.ClearBufferMask;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class PlainTest implements EntryPoint {
	private Surface3D surface;

	@Override
	public void onModuleLoad() {
		// create surface per configuration
		WebGLContextAttributes contextAttribs = new WebGLContextAttributes();
		surface = new Surface3D(500, 500, contextAttribs);
		RootPanel.get().add(surface);

		// check whether WebGL is supported
		final GL2 gl = surface.getGL();
		if (gl == null) {
			throw new RuntimeException("WebGL not supported");
		}

		// set initial viewport to cover entire surface and
		gl.viewport(0, 0, surface.getWidth(), surface.getHeight());
		setupLoop();
	}

	private void setupLoop() {
		// setup rendering timer
		FpsTimer timer = new FpsTimer(60) {
			@Override
			public void update() {
				GL2 gl = surface.getGL();
				gl.clearColor((float)Math.random(), 0, 0, 1);
				gl.clear(ClearBufferMask.COLOR_BUFFER_BIT);
			}
		};
		timer.start();
	}
}
