package com.vector.common.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public interface OrderNoGenerator {

    String generate(String prefix);

    String generate(String prefix, int suffixLength);
}
