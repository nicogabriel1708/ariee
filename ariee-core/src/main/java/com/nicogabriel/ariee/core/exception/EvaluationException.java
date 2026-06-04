package com.nicogabriel.ariee.core.exception;

import com.nicogabriel.ariee.core.internal.util.Strings;

public final class EvaluationException extends PipelineException {

    public EvaluationException(String message, int position) {
        super(Strings.formatSentence(message), position);
    }
}
