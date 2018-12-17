package com.xiangbinwang.common;

import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Internal.EnumLite;

public interface ProtocolMessageEnum extends EnumLite {
  int getNumber();

  EnumValueDescriptor getValueDescriptor();

  EnumDescriptor getDescriptorForType();
}
