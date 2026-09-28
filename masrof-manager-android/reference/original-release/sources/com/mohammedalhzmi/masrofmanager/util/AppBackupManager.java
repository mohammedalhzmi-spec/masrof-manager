package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p012io.ByteStreamsKt;
import kotlin.p012io.CloseableKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AppBackupManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\r\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ \u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¨\u0006\u0014"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/AppBackupManager;", "", "<init>", "()V", "createBackup", "Ljava/io/File;", "context", "Landroid/content/Context;", "exportToUri", "", "uri", "Landroid/net/Uri;", "shareBackup", "restoreFromUri", "addFile", "zip", "Ljava/util/zip/ZipOutputStream;", "file", "name", "", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class AppBackupManager {
    public static final int $stable = 0;
    public static final AppBackupManager INSTANCE = new AppBackupManager();

    private AppBackupManager() {
    }

    public final File createBackup(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        File file = new File(context.getFilesDir(), "masrof-backup-" + System.currentTimeMillis() + ".zip");
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
        try {
            ZipOutputStream zipOutputStream2 = zipOutputStream;
            AppBackupManager appBackupManager = INSTANCE;
            File databasePath = context.getDatabasePath("masrof-db");
            Intrinsics.checkNotNullExpressionValue(databasePath, "getDatabasePath(...)");
            appBackupManager.addFile(zipOutputStream2, databasePath, "databases/masrof-db");
            appBackupManager.addFile(zipOutputStream2, new File(context.getDatabasePath("masrof-db").getParentFile(), "masrof-db-wal"), "databases/masrof-db-wal");
            appBackupManager.addFile(zipOutputStream2, new File(context.getDatabasePath("masrof-db").getParentFile(), "masrof-db-shm"), "databases/masrof-db-shm");
            appBackupManager.addFile(zipOutputStream2, new File(context.getApplicationInfo().dataDir, "shared_prefs/masrof_preferences.xml"), "shared_prefs/masrof_preferences.xml");
            zipOutputStream2.putNextEntry(new ZipEntry("backup-info.txt"));
            byte[] bytes = "Masrof Manager portable backup\n".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            zipOutputStream2.write(bytes);
            zipOutputStream2.closeEntry();
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(zipOutputStream, null);
            return file;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(zipOutputStream, th);
                throw th2;
            }
        }
    }

    public final void exportToUri(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        FileInputStream fileInputStream = new FileInputStream(createBackup(context));
        try {
            FileInputStream fileInputStream2 = fileInputStream;
            OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uri);
            try {
                OutputStream outputStream = outputStreamOpenOutputStream;
                Intrinsics.checkNotNull(outputStream);
                ByteStreamsKt.copyTo$default(fileInputStream2, outputStream, 0, 2, null);
                CloseableKt.closeFinally(outputStreamOpenOutputStream, null);
                CloseableKt.closeFinally(fileInputStream, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(outputStreamOpenOutputStream, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(fileInputStream, th3);
                throw th4;
            }
        }
    }

    public final void shareBackup(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".files", createBackup(context));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("application/zip");
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        intent.addFlags(1);
        context.startActivity(Intent.createChooser(intent, "مشاركة النسخة الاحتياطية ZIP"));
    }

    public final void restoreFromUri(Context context, Uri uri) throws FileNotFoundException {
        File file;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        File file2 = new File(context.getCacheDir(), "restore.zip");
        InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
        try {
            InputStream inputStream = inputStreamOpenInputStream;
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                Intrinsics.checkNotNull(inputStream);
                ByteStreamsKt.copyTo$default(inputStream, fileOutputStream, 0, 2, null);
                CloseableKt.closeFinally(fileOutputStream, null);
                CloseableKt.closeFinally(inputStreamOpenInputStream, null);
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file2));
                try {
                    ZipInputStream zipInputStream2 = zipInputStream;
                    for (ZipEntry nextEntry = zipInputStream2.getNextEntry(); nextEntry != null; nextEntry = zipInputStream2.getNextEntry()) {
                        String name = nextEntry.getName();
                        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                        if (StringsKt.startsWith$default(name, "databases/", false, 2, (Object) null)) {
                            File parentFile = context.getDatabasePath("masrof-db").getParentFile();
                            String name2 = nextEntry.getName();
                            Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                            file = new File(parentFile, StringsKt.removePrefix(name2, (CharSequence) "databases/"));
                        } else {
                            String name3 = nextEntry.getName();
                            Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
                            file = StringsKt.startsWith$default(name3, "shared_prefs/", false, 2, (Object) null) ? new File(context.getApplicationInfo().dataDir, nextEntry.getName()) : null;
                        }
                        if (file != null) {
                            File parentFile2 = file.getParentFile();
                            if (parentFile2 != null) {
                                parentFile2.mkdirs();
                            }
                            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                            try {
                                ByteStreamsKt.copyTo$default(zipInputStream2, fileOutputStream2, 0, 2, null);
                                CloseableKt.closeFinally(fileOutputStream2, null);
                            } catch (Throwable th) {
                                try {
                                    throw th;
                                } catch (Throwable th2) {
                                    CloseableKt.closeFinally(fileOutputStream2, th);
                                    throw th2;
                                }
                            }
                        }
                        zipInputStream2.closeEntry();
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(zipInputStream, null);
                    file2.delete();
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(zipInputStream, th3);
                        throw th4;
                    }
                }
            } catch (Throwable th5) {
                try {
                    throw th5;
                } catch (Throwable th6) {
                    CloseableKt.closeFinally(fileOutputStream, th5);
                    throw th6;
                }
            }
        } catch (Throwable th7) {
            try {
                throw th7;
            } catch (Throwable th8) {
                CloseableKt.closeFinally(inputStreamOpenInputStream, th7);
                throw th8;
            }
        }
    }

    private final void addFile(ZipOutputStream zip, File file, String name) throws IOException {
        if (file.exists()) {
            zip.putNextEntry(new ZipEntry(name));
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                ByteStreamsKt.copyTo$default(fileInputStream, zip, 0, 2, null);
                CloseableKt.closeFinally(fileInputStream, null);
                zip.closeEntry();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileInputStream, th);
                    throw th2;
                }
            }
        }
    }
}
