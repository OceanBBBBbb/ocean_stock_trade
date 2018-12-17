package com.xiangbinwang.common;

import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.ProtocolMessageEnum;

public class TrdCommon {

  private static FileDescriptor descriptor;

  public static FileDescriptor getDescriptor() {
    return descriptor;
  }


  public static enum TrdMarket implements ProtocolMessageEnum {
    TrdMarket_Unknown(0, 0),
    TrdMarket_HK(1, 1),
    TrdMarket_US(2, 2),
    TrdMarket_CN(3, 3),
    TrdMarket_HKCC(4, 4);

    public static final int TrdMarket_Unknown_VALUE = 0;
    public static final int TrdMarket_HK_VALUE = 1;
    public static final int TrdMarket_US_VALUE = 2;
    public static final int TrdMarket_CN_VALUE = 3;
    public static final int TrdMarket_HKCC_VALUE = 4;
    private static EnumLiteMap<TrdCommon.TrdMarket> internalValueMap = new EnumLiteMap<TrdCommon.TrdMarket>() {
      public TrdCommon.TrdMarket findValueByNumber(int number) {
        return TrdCommon.TrdMarket.valueOf(number);
      }
    };
    private static final TrdCommon.TrdMarket[] VALUES = values();
    private final int index;
    private final int value;

    public final int getNumber() {
      return this.value;
    }

    public static TrdCommon.TrdMarket valueOf(int value) {
      switch(value) {
        case 0:
          return TrdMarket_Unknown;
        case 1:
          return TrdMarket_HK;
        case 2:
          return TrdMarket_US;
        case 3:
          return TrdMarket_CN;
        case 4:
          return TrdMarket_HKCC;
        default:
          return null;
      }
    }

    public static EnumLiteMap<TrdCommon.TrdMarket> internalGetValueMap() {
      return internalValueMap;
    }

    public final EnumValueDescriptor getValueDescriptor() {
      return (EnumValueDescriptor)getDescriptor().getValues().get(this.index);
    }

    public final EnumDescriptor getDescriptorForType() {
      return getDescriptor();
    }

    public static final EnumDescriptor getDescriptor() {
      return (EnumDescriptor)TrdCommon.getDescriptor().getEnumTypes().get(1);
    }

    public static TrdCommon.TrdMarket valueOf(EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
        return VALUES[desc.getIndex()];
      }
    }

    private TrdMarket(int index, int value) {
      this.index = index;
      this.value = value;
    }
  }

  public static enum TrdSide implements ProtocolMessageEnum {
    TrdSide_Unknown(0, 0),
    TrdSide_Buy(1, 1),
    TrdSide_Sell(2, 2),
    TrdSide_SellShort(3, 3),
    TrdSide_BuyBack(4, 4);

    public static final int TrdSide_Unknown_VALUE = 0;
    public static final int TrdSide_Buy_VALUE = 1;
    public static final int TrdSide_Sell_VALUE = 2;
    public static final int TrdSide_SellShort_VALUE = 3;
    public static final int TrdSide_BuyBack_VALUE = 4;
    private static EnumLiteMap<TrdCommon.TrdSide> internalValueMap = new EnumLiteMap<TrdCommon.TrdSide>() {
      public TrdCommon.TrdSide findValueByNumber(int number) {
        return TrdCommon.TrdSide.valueOf(number);
      }
    };
    private static final TrdCommon.TrdSide[] VALUES = values();
    private final int index;
    private final int value;

    public final int getNumber() {
      return this.value;
    }

    public static TrdCommon.TrdSide valueOf(int value) {
      switch(value) {
        case 0:
          return TrdSide_Unknown;
        case 1:
          return TrdSide_Buy;
        case 2:
          return TrdSide_Sell;
        case 3:
          return TrdSide_SellShort;
        case 4:
          return TrdSide_BuyBack;
        default:
          return null;
      }
    }

    public static EnumLiteMap<TrdCommon.TrdSide> internalGetValueMap() {
      return internalValueMap;
    }

    public final EnumValueDescriptor getValueDescriptor() {
      return (EnumValueDescriptor)getDescriptor().getValues().get(this.index);
    }

    public final EnumDescriptor getDescriptorForType() {
      return getDescriptor();
    }

    public static final EnumDescriptor getDescriptor() {
      return (EnumDescriptor)TrdCommon.getDescriptor().getEnumTypes().get(3);
    }

    public static TrdCommon.TrdSide valueOf(EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
        return VALUES[desc.getIndex()];
      }
    }

    private TrdSide(int index, int value) {
      this.index = index;
      this.value = value;
    }
  }

  public static enum OrderType implements ProtocolMessageEnum {
    OrderType_Unknown(0, 0),
    OrderType_Normal(1, 1),
    OrderType_Market(2, 2),
    OrderType_AbsoluteLimit(3, 5),
    OrderType_Auction(4, 6),
    OrderType_AuctionLimit(5, 7),
    OrderType_SpecialLimit(6, 8);

    public static final int OrderType_Unknown_VALUE = 0;
    public static final int OrderType_Normal_VALUE = 1;
    public static final int OrderType_Market_VALUE = 2;
    public static final int OrderType_AbsoluteLimit_VALUE = 5;
    public static final int OrderType_Auction_VALUE = 6;
    public static final int OrderType_AuctionLimit_VALUE = 7;
    public static final int OrderType_SpecialLimit_VALUE = 8;
    private static EnumLiteMap<TrdCommon.OrderType> internalValueMap = new EnumLiteMap<TrdCommon.OrderType>() {
      public TrdCommon.OrderType findValueByNumber(int number) {
        return TrdCommon.OrderType.valueOf(number);
      }
    };
    private static final TrdCommon.OrderType[] VALUES = values();
    private final int index;
    private final int value;

    public final int getNumber() {
      return this.value;
    }

    public static TrdCommon.OrderType valueOf(int value) {
      switch(value) {
        case 0:
          return OrderType_Unknown;
        case 1:
          return OrderType_Normal;
        case 2:
          return OrderType_Market;
        case 3:
        case 4:
        default:
          return null;
        case 5:
          return OrderType_AbsoluteLimit;
        case 6:
          return OrderType_Auction;
        case 7:
          return OrderType_AuctionLimit;
        case 8:
          return OrderType_SpecialLimit;
      }
    }

    public static EnumLiteMap<TrdCommon.OrderType> internalGetValueMap() {
      return internalValueMap;
    }

    public final EnumValueDescriptor getValueDescriptor() {
      return (EnumValueDescriptor)getDescriptor().getValues().get(this.index);
    }

    public final EnumDescriptor getDescriptorForType() {
      return getDescriptor();
    }

    public static final EnumDescriptor getDescriptor() {
      return (EnumDescriptor)TrdCommon.getDescriptor().getEnumTypes().get(4);
    }

    public static TrdCommon.OrderType valueOf(EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
      } else {
        return VALUES[desc.getIndex()];
      }
    }

    private OrderType(int index, int value) {
      this.index = index;
      this.value = value;
    }
  }

  public interface EnumLite {
    int getNumber();
  }
}
