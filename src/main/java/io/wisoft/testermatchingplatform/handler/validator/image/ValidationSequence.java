package io.wisoft.testermatchingplatform.handler.validator.image;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, Custom.class})
public interface ValidationSequence {
}
