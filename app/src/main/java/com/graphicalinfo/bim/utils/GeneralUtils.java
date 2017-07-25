package com.graphicalinfo.bim.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.view.Window;
import android.widget.Toast;

import com.graphicalinfo.bim.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ayoob on 25/07/17.
 */

public class GeneralUtils {
    public static DisplayStats displayStats;

    public static boolean saveArray(SharedPreferences prefs, String[] array, String arrayName) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    public static boolean saveList(SharedPreferences prefs, List<String> list, String listName) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(listName +"_size", list.size());
        int i=0;
        for(String s : list) {
            editor.putString(listName + "_" + i, s);
            ++i;
        }
        return editor.commit();
    }

    public static boolean saveSet(SharedPreferences prefs, Set<String> set, String setName) {
        SharedPreferences.Editor editor = prefs.edit();
        final int setSize = set.size();
        editor.putInt(setName +"_size", setSize);
        int i=0;
        for(String s : set)  {
            editor.putString(setName + "_" + i, s);
            ++i;
        }
        return editor.commit();
    }

    public static String[] loadArray(SharedPreferences prefs, String arrayName) {
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }

    public static LinkedList<String> loadList(SharedPreferences prefs, String listName) {
        int size = prefs.getInt(listName + "_size", 0);
        LinkedList<String> list = new LinkedList<String>();
        for(int i=0;i<size;i++)  {
            list.add(prefs.getString(listName + "_" + i, null));
        }
        return list;
    }

    public static Set<String> loadSet(SharedPreferences prefs, String setName) {
        final int size = prefs.getInt(setName + "_size", 0);
        Set<String> set = new HashSet<String>(size);
        for(int i=0;i<size;i++)
            set.add(prefs.getString(setName + "_" + i, null));
        return set;
    }

    public static boolean existsSet(SharedPreferences prefs, String setName) {
        return prefs.contains(setName + "_size");
    }

    public static void deleteSet(SharedPreferences prefs, String setName) {
        final int size = prefs.getInt(setName + "_size", 0);
        SharedPreferences.Editor editor = prefs.edit();
        for(int i=0;i<size;i++)
            editor.remove(setName + "_" + i);
        editor.remove(setName + "_size");
        editor.commit();
    }

    static int calculateInSampleSize(BitmapFactory.Options bitmapOptions, int reqWidth, int reqHeight) {
        final int height = bitmapOptions.outHeight;
        final int width = bitmapOptions.outWidth;
        int sampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            sampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return sampleSize;
    }





    public static String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    public static Intent newEmailIntent(String toAddress, String subject, String body, String cc) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + Uri.encode(toAddress) + "?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(body) + "&cc=" + Uri.encode(cc)));
        return intent;
    }

    public static Intent newTelIntent(String telurl) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        // FIXME : need to check XXX is really a short number in tel:XXX
        intent.setData(Uri.parse(telurl));
        return intent;
    }

    public static String getBuildInfo(Context context) {
        // get app version info
        String appVersion = "";
        PackageInfo pInfo;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            appVersion = pInfo.versionName + " (" + pInfo.versionCode + ")\n";
        } catch (PackageManager.NameNotFoundException e) {}

        String board = "Board: " + Build.BOARD + "\n";
        String bootloader = "Bootloader: " + Build.BOOTLOADER + "\n";
        String brand = "Brand: " + Build.BRAND + "\n";
        String device = "Device: " + Build.DEVICE + "\n";
        String display = "Display: " + Build.DISPLAY + "\n";
        String product = "Product: " + Build.PRODUCT + "\n";
        String model = "Model: " + Build.MODEL + "\n";
        String manufacturer = "Manufacturer: " + Build.MANUFACTURER + "\n";

        return appVersion + board + bootloader + brand + device + display + product + model + manufacturer;
    }

    public static Bitmap roundCorners(Bitmap bitmap, float radius) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }



    public static void launchApp(Context context, String packageName) {
        Intent mIntent = context.getPackageManager().getLaunchIntentForPackage(
                packageName);
        if (mIntent != null) {
            try {
                context.startActivity(mIntent);
            } catch (ActivityNotFoundException err) {
                Toast t = Toast.makeText(context,
                        R.string.ErrorAppNotFound, Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }

    /**
     * Checks to see if URL is DuckDuckGo SERP
     * Returns the query if it's a SERP, otherwise null
     *
     * @param url
     * @return
     */
    static public String getQueryIfSerp(String url) {
        if(!isSerpUrl(url)) {
            return null;
        }

        Uri uri = Uri.parse(url);
        String query = uri.getQueryParameter("q");
        if(query != null)
            return query;

        String lastPath = uri.getLastPathSegment();
        if(lastPath == null)
            return null;

        if(!lastPath.contains(".html")) {
            return lastPath.replace("_", " ");
        }

        return null;
    }

    public static boolean isSerpUrl(String url) {
        return url.contains("duckduckgo.com");
    }

    /**
     * Read cached sources from file cache
     *
     * @return Cached source set, if not readable from cache returns null
     */


    private static boolean isIntentSafe(Context context, Intent intent) {
        // Verify it resolves
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return activities.size() > 0;
    }

    public static void execIntentIfSafe(Context context, Intent intent) {
        if(GeneralUtils.isIntentSafe(context, intent)) {
            context.startActivity(intent);
        }
        else {
            Toast.makeText(context, R.string.ErrorActivityNotFound, Toast.LENGTH_SHORT).show();
        }
    }

    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch(PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch(PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }



    public static String getUrlToDisplay(String url) {
        if(url==null || url.length()==0) {
            return "";
        }
        if (url.startsWith("https://")) {
            url = url.replace("https://", "");
        } else if (url.startsWith("http://")) {
            url = url.replace("http://", "");
        }
        if (url.startsWith("www.")) {
            url = url.replace("www.", "");
        }
        return url;
    }



    public static int getStatusBarHeight(Activity activity) {
        Rect rect = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    public static int getNavigationBarHeight(Context context) {
        int id = context.getResources().getIdentifier(
                context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT? "navigation_bar_height" : "navigation_bar_height_landscape",
                "dimen", "android");
        if (id > 0) {
            return context.getResources().getDimensionPixelSize(id);
        }
        return 0;
    }

    public static boolean isValidIpAddress(String input) {
        String ipv4Regex = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
        String ipv6Regex = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";
        Pattern ipv4Pattern = Pattern.compile(ipv4Regex);
        Pattern ipv6Pattern = Pattern.compile(ipv6Regex);

        Matcher ipv4Matcher = ipv4Pattern.matcher(input);
        if(ipv4Matcher.matches()) return true;
        Matcher ipv6Matcher = ipv6Pattern.matcher(input);
        return ipv6Matcher.matches();
    }

}
