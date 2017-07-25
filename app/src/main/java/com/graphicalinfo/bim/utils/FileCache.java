package com.graphicalinfo.bim.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ayoob on 25/07/17.
 */

public class FileCache {

    protected final String TAG = "FileCache";

    private final File cacheDirectory;
    private final File externalImageDirectory;
    private final Context context;

    public FileCache(Context context) {
        this.context = context;
        cacheDirectory = this.context.getCacheDir();

        // deprecated from now on 2013-06-04 (Version Code: 45)
        externalImageDirectory = this.context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    }

    public boolean saveBitmapAsFile(String name, Bitmap bitmap) {
        File saveFile = new File(cacheDirectory, name);

        boolean saved = false;
        FileOutputStream os = null;
        try {
            Log.d("FileCache", "Saving File To Cache " + saveFile.getPath());
            os = new FileOutputStream(saveFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.flush();
            os.close();
            saved = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return saved;
    }



    public boolean saveStringToInternal(String name, String file){
        try {
            FileOutputStream fos = this.context.openFileOutput(name, Context.MODE_PRIVATE);
            fos.write(file.getBytes());
            fos.close();

            return true;
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return false;
    }





    public String getPath(String name) {
        File f =  this.context.getFileStreamPath(name);
        if(f != null) {
            return f.getAbsolutePath();
        }
        return null;
    }

    public FileDescriptor getFd(String name) {
        try {
            return this.context.openFileInput(name).getFD();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FileInputStream getFileInputStream(String name) {
        try {
            return this.context.openFileInput(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeFile(String name) {
        this.context.deleteFile(name);
    }

    public String getStringFromInternal(String name){
        String result = null;

        try {
            FileInputStream fis = this.context.openFileInput(name);
            result = GeneralUtils.readStream(fis);
            fis.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return result;
    }

    public void processFromInternal(String name, FileProcessor processor) {

        try {
            FileInputStream fis = this.context.openFileInput(name);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                processor.processLine(strLine);
            }
            fis.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public void clearCache() {
        if(cacheDirectory!=null && cacheDirectory.isDirectory()) {
            deleteDir(cacheDirectory);
        }
    }

    private boolean deleteDir(File dir) {
        if(dir!=null && dir.isDirectory()) {
            String[] children = dir.list();
            for(String child : children) {
                boolean success = deleteDir(new File(dir, child));
                if(!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * Remove files that have become unnecessary upon migration
     */
    public void removeThrashOnMigration() {
        if(this.externalImageDirectory != null) {
            File[] files = this.externalImageDirectory.listFiles();
            if(files != null) {
                for(File file : files) {
                    file.delete();
                }
            }
        }
    }
}
