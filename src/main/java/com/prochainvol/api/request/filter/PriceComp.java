package com.prochainvol.api.request.filter;

import java.util.Comparator;

public class PriceComp implements Comparator<Float> {
    @Override
    public int compare(Float f1, Float f2) {
      if(f1 >= f2 ){
          return 1;
      }else{
          return -1;
      }
    }
}
