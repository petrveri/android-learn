package com.et4334.appofpetr.api.storage;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by et4334 on 7/13/15.
 */
public class InternalCacheHelper extends FilesHelper {

    @Override
    protected File getRootDirectory(Context ctx) {
        return ctx.getCacheDir();
    }

    @Override
    protected FileInputStream getFileInputStream(Context ctx, String fileName) throws FileNotFoundException {
        File file = new File(
                ctx.getCacheDir()
                        .getAbsolutePath(), fileName);
        return new FileInputStream(file);
    }

    @Override
    protected FileOutputStream getFileOutputStream(Context ctx, String fileName) throws FileNotFoundException {
        File file = new File(
                ctx.getCacheDir()
                        .getAbsolutePath(), fileName);
        return new FileOutputStream(file);
    }

}
