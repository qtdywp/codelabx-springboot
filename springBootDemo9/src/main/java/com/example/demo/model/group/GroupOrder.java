package com.example.demo.model.group;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({GroupA.class, GroupB.class, Default.class})
public interface GroupOrder
{
}
