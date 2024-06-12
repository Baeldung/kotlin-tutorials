package arrays_nullability;

import org.jetbrains.annotations.Nullable;

public class StringUtils {

  public static @Nullable String fromCharArray(char[] s) {
    if (s == null || s.length == 0) return null;

    return new String(s);
  }

  public static char @Nullable [] toCharArray(String s) {
    if (s == null) return null;

    return s.toCharArray();
  }
}
