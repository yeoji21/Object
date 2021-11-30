package com.choi;

import java.util.function.Function;

public class Either<L, R>{
    private L leftValue;
    private R rightValue;

    public Either(L leftValue, R rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public boolean isLeft() {
        return leftValue != null;
    }

    public boolean isRight() {
        return rightValue != null;
    }

    public <R2> Either<L, R2> map(Function<R, R2> func) {
        if (isLeft()) return Either.left(leftValue);
        else return Either.right(func.apply(rightValue));
    }

    public <R2> Either<L, R2> flatMap(Function<R, Either<L, R2>> func) {
        if (this.isRight()) {
            return func.apply(rightValue);
        } else {
            return Either.left(leftValue);
        }
    }

    public static <L,R> Either<L,R> left(L left){
        return new Either<>(left, null);
    }

    public static <L,R> Either<L,R> right(R right){
        return new Either<>(null, right);
    }

    public L getLeft() {
        return leftValue;
    }

    public R getRight() {
        return rightValue;
    }
}

