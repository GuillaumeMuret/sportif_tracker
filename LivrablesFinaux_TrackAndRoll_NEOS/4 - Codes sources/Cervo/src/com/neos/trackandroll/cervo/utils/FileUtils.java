package com.neos.trackandroll.cervo.utils;

import java.io.File;

public class FileUtils {

    public static final String ROOT_DATA_DIRECTOR = "TrackAndRollData";

      /**
       * Process called to get the root file
       *
       * @return : the root file
       */
      public static File getRootFile() {
          File root = new File(ROOT_DATA_DIRECTOR);
          if (!root.exists()) {
              root.mkdirs();
          }
          return root;
      }
}
