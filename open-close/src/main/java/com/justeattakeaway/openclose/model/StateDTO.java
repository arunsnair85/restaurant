package com.justeattakeaway.openclose.model;

import javax.validation.constraints.NotNull;
//TODO validations

public record StateDTO(@NotNull boolean isOpen) {

}
