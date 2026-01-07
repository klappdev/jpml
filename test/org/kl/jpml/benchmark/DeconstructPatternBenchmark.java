/*
 * Licensed under the MIT License <http://opensource.org/licenses/MIT>.
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2019 - 2026 https://github.com/klappdev
 *
 * Permission is hereby  granted, free of charge, to any  person obtaining a copy
 * of this software and associated  documentation files (the "Software"), to deal
 * in the Software  without restriction, including without  limitation the rights
 * to  use, copy,  modify, merge,  publish, distribute,  sublicense, and/or  sell
 * copies  of  the Software,  and  to  permit persons  to  whom  the Software  is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE  IS PROVIDED "AS  IS", WITHOUT WARRANTY  OF ANY KIND,  EXPRESS OR
 * IMPLIED,  INCLUDING BUT  NOT  LIMITED TO  THE  WARRANTIES OF  MERCHANTABILITY,
 * FITNESS FOR  A PARTICULAR PURPOSE AND  NONINFRINGEMENT. IN NO EVENT  SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS  BE  LIABLE FOR  ANY  CLAIM,  DAMAGES OR  OTHER
 * LIABILITY, WHETHER IN AN ACTION OF  CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE  OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.kl.jpml.benchmark;

import static org.kl.jpml.pattern.DeconstructPattern.match;

import java.util.concurrent.TimeUnit;

import org.kl.jpml.test.shape.Circle;
import org.kl.jpml.test.shape.Figure;
import org.kl.jpml.test.shape.Parallelepiped;
import org.kl.jpml.test.shape.Quadrate;
import org.kl.jpml.test.shape.Rectangle;
import org.kl.jpml.test.shape.Triangle;
import org.kl.jpml.test.shape.Tripiped;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 1)
@Fork(3)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class DeconstructPatternBenchmark {
	private Figure circleFigure;
	private Figure quadrateFigure;
	private Figure rectangleFigure;
	private Figure tripipedFigure;
	
	@Setup
    public void setup() {
		this.circleFigure = new Circle(5);
		this.quadrateFigure = new Quadrate(15);
		this.rectangleFigure = new Rectangle(10, 12);
		this.tripipedFigure = new Tripiped(5, 5, 5);
	}
	
	@Benchmark
    public int matchDeconstructUnExpressionPlain() {
		if (circleFigure instanceof Circle) {
			int r = ((Circle) circleFigure).radius();
			
			return 2 * (r + r);
		} else {
			return 0;
		}
	}
	
	@Benchmark
    public int matchDeconstructUnExpressionTwoFieldsPlain() {
		if (rectangleFigure instanceof Rectangle) {
			int w = ((Rectangle) rectangleFigure).width();
			int h = ((Rectangle) rectangleFigure).height();
			
			return (w * h);
		} else {
			return 0;
		}
	}
	
	@Benchmark
    public int matchDeconstructUnExpressionThreeFieldsPlain() {
		if (rectangleFigure instanceof Tripiped) {
			float w = ((Tripiped) tripipedFigure).width();
			float l = ((Tripiped) tripipedFigure).longitude();
			float h = ((Tripiped) tripipedFigure).height();
			
			return (int) (w * l * h);
		} else {
			return 0;
		}
	}
	
	@Benchmark
    public int matchDeconstructBiExpressionPlain() {
		if (quadrateFigure instanceof Circle) {
			int r = ((Circle) quadrateFigure).radius();
			
			return 2 * (r + r);
		} else if (quadrateFigure instanceof Quadrate) {
			int w = ((Quadrate) quadrateFigure).width();
			
			return 2 * (w * w);
		} else {
			return 0;
		}
	}
	
	@Benchmark
    public int matchDeconstructBiExpressionTwoFieldsPlain() {
		if (rectangleFigure instanceof Triangle) {
			double w = ((Triangle) rectangleFigure).width();
			double h = ((Triangle) rectangleFigure).height();
			
			return (int) (w * h);
		} else if (rectangleFigure instanceof Rectangle) {
			int w = ((Rectangle) rectangleFigure).width();
			int h = ((Rectangle) rectangleFigure).height();
			
			return (w * h);
		} else {
			return 0;
		}
	}
	
	@Benchmark
    public int matchDeconstructBiExpressionThreeFieldsPlain() {
		if (tripipedFigure instanceof Parallelepiped) {
			short w = ((Parallelepiped) tripipedFigure).width();
			short l = ((Parallelepiped) tripipedFigure).longitude();
			short h = ((Parallelepiped) tripipedFigure).height();
			
			return (int) (w * l * h);
		} else if (tripipedFigure instanceof Tripiped) {
			float w = ((Tripiped) tripipedFigure).width();
			float l = ((Tripiped) tripipedFigure).longitude();
			float h = ((Tripiped) tripipedFigure).height();
			
			return (int) (w * l * h);
		} else {
			return 0;
		}
	}
	
	@Benchmark
    public int matchDeconstructUnExpressionReflective() {
		return match(circleFigure,
                Circle.class, (Integer r) -> 2 * (r + r)
        );
	}
	
	@Benchmark
    public int matchDeconstructUnExpressionTwoFieldsReflective() {
		return match(rectangleFigure,
                Rectangle.class, (Integer w, Integer h) -> w * h
        );
	}
	
	@Benchmark
    public int matchDeconstructUnExpressionThreeFieldsReflective() {
		return match(tripipedFigure,
                Tripiped.class, (Float w, Float l, Float h) -> (int) (w * l * h)
        );
	}
	
	@Benchmark
    public int matchDeconstructBiExpressionReflective() {
        return match(quadrateFigure,
                Circle.class, (Integer r) -> 2 * (r + r),
                Quadrate.class, (Integer a) -> a * a
        );
	}
	
	@Benchmark
    public int matchDeconstructBiExpressionTwoFieldsReflective() {
		return match(rectangleFigure,
                Triangle.class, (Double w, Double h) -> (int) (w * h),
                Rectangle.class, (Integer w, Integer h) -> w * h
        );
	}
	
	@Benchmark
    public int matchDeconstructBiExpressionThreeFieldsReflective() {
		return match(tripipedFigure,
                Parallelepiped.class, (Short w, Short l, Short h) -> (int) (w * l * h),
                Tripiped.class, (Float w, Float l, Float h) -> (int) (w * l * h)
        );
	}
}
