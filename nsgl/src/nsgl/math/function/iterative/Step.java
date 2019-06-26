package nsgl.math.function.iterative;

import nsgl.math.Function;

public abstract class Step<I,O> extends Function<O,O>{
    public abstract O init(I input);
}
