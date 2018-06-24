package com.smule.singandroid.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.smule.android.logging.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageToDiskUtils {
    private static final String f24759a = ImageToDiskUtils.class.getName();

    public static Bitmap m25830a(Context context, String str) {
        FileStorage.m25828d(context.getCacheDir().getPath());
        return m25831a(str);
    }

    public static boolean m25836a(Context context, String str, Bitmap bitmap) {
        FileStorage.m25828d(context.getCacheDir().getPath());
        return m25837a(str, bitmap);
    }

    public static void m25838b(Context context, String str) {
        FileStorage.m25828d(context.getCacheDir().getPath());
        FileStorage.m25827c(str);
    }

    private static Bitmap m25831a(String str) {
        try {
            InputStream b = FileStorage.m25826b(str);
            if (b != null && b.available() > 0) {
                return BitmapFactory.decodeStream(b);
            }
            Log.d(f24759a, "Can't load from external storage");
            return null;
        } catch (Throwable e) {
            Log.d(f24759a, "error in loadIcon", e);
            return null;
        }
    }

    private static boolean m25837a(String str, Bitmap bitmap) {
        try {
            OutputStream a = FileStorage.m25825a(str);
            if (a != null) {
                bitmap.compress(CompressFormat.JPEG, 100, a);
                return true;
            }
            Log.d(f24759a, "Can't save to external storage");
            return false;
        } catch (Throwable e) {
            Log.d(f24759a, "error in saveIcon", e);
            return false;
        }
    }

    public static Uri m25832a(Context context, Intent intent) {
        Uri data = intent.getData();
        Log.b(f24759a, "getPathFromPicker:" + data);
        String a = m25835a(context, data);
        if (a != null) {
            try {
                ParcelFileDescriptor open = ParcelFileDescriptor.open(new File(a), 268435456);
                if (open != null) {
                    open.close();
                    data = Uri.fromFile(new File(a));
                }
            } catch (FileNotFoundException e) {
                Log.b(f24759a, "getPathFromPicker:" + a + " FileNotFoundException:" + e);
            } catch (IOException e2) {
                Log.b(f24759a, "getPathFromPicker:" + a + " IOException:" + e2);
            }
        }
        return data;
    }

    private static String m25835a(Context context, Uri uri) {
        String string;
        Throwable th;
        Cursor cursor = null;
        ContentResolver contentResolver = context.getContentResolver();
        Cursor query;
        try {
            query = contentResolver.query(uri, new String[]{"_data"}, null, null, null);
            if (query != null) {
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                    query.moveToFirst();
                    string = query.getString(columnIndexOrThrow);
                    if (query == null) {
                        return string;
                    }
                    query.close();
                    return string;
                } catch (IllegalArgumentException e) {
                    cursor = query;
                    try {
                        string = m25834a(context, contentResolver, uri);
                        if (cursor != null) {
                            return string;
                        }
                        cursor.close();
                        return string;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (SecurityException e2) {
                    if (query != null) {
                        query.close();
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (IllegalArgumentException e3) {
            string = m25834a(context, contentResolver, uri);
            if (cursor != null) {
                return string;
            }
            cursor.close();
            return string;
        } catch (SecurityException e4) {
            query = null;
            if (query != null) {
                query.close();
            }
            return null;
        }
    }

    private static String m25833a(Context context) throws IOException {
        return File.createTempFile("prefix", "extension", context.getCacheDir()).getAbsolutePath();
    }

    private static String m25834a(Context context, ContentResolver contentResolver, Uri uri) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream2;
        Throwable th;
        if (uri == null) {
            return null;
        }
        FileInputStream fileInputStream3 = null;
        FileOutputStream fileOutputStream2 = null;
        try {
            ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(uri, "r");
            if (openFileDescriptor == null) {
                if (null != null) {
                    try {
                        fileInputStream3.close();
                    } catch (Exception e) {
                    }
                }
                if (null == null) {
                    return null;
                }
                try {
                    fileOutputStream2.close();
                    return null;
                } catch (Exception e2) {
                    return null;
                }
            }
            fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
            try {
                String a = m25833a(context);
                fileOutputStream2 = new FileOutputStream(a);
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, read);
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Exception e4) {
                        }
                    }
                    return a;
                } catch (IOException e5) {
                    fileOutputStream = fileOutputStream2;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Exception e6) {
                        }
                    }
                    if (fileOutputStream != null) {
                        return null;
                    }
                    try {
                        fileOutputStream.close();
                        return null;
                    } catch (Exception e7) {
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e8) {
                        }
                    }
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Exception e9) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e10) {
                fileOutputStream = null;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    return null;
                }
                fileOutputStream.close();
                return null;
            } catch (Throwable th3) {
                fileOutputStream2 = null;
                th = th3;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        } catch (IOException e11) {
            fileOutputStream = null;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream != null) {
                return null;
            }
            fileOutputStream.close();
            return null;
        } catch (Throwable th32) {
            fileOutputStream2 = null;
            fileInputStream = null;
            th = th32;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }
}
