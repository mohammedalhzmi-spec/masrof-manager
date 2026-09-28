package com.mohammedalhzmi.masrofmanager.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdateCenter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001a"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/UpdateInfo;", "", "versionCode", "", "versionName", "", "apkUrl", "notes", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getVersionCode", "()I", "getVersionName", "()Ljava/lang/String;", "getApkUrl", "getNotes", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final /* data */ class UpdateInfo {
    public static final int $stable = 0;
    private final String apkUrl;
    private final String notes;
    private final int versionCode;
    private final String versionName;

    public static /* synthetic */ UpdateInfo copy$default(UpdateInfo updateInfo, int i, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = updateInfo.versionCode;
        }
        if ((i2 & 2) != 0) {
            str = updateInfo.versionName;
        }
        if ((i2 & 4) != 0) {
            str2 = updateInfo.apkUrl;
        }
        if ((i2 & 8) != 0) {
            str3 = updateInfo.notes;
        }
        return updateInfo.copy(i, str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getVersionCode() {
        return this.versionCode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getVersionName() {
        return this.versionName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getApkUrl() {
        return this.apkUrl;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    public final UpdateInfo copy(int versionCode, String versionName, String apkUrl, String notes) {
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        Intrinsics.checkNotNullParameter(apkUrl, "apkUrl");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new UpdateInfo(versionCode, versionName, apkUrl, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpdateInfo)) {
            return false;
        }
        UpdateInfo updateInfo = (UpdateInfo) other;
        return this.versionCode == updateInfo.versionCode && Intrinsics.areEqual(this.versionName, updateInfo.versionName) && Intrinsics.areEqual(this.apkUrl, updateInfo.apkUrl) && Intrinsics.areEqual(this.notes, updateInfo.notes);
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.versionCode) * 31) + this.versionName.hashCode()) * 31) + this.apkUrl.hashCode()) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "UpdateInfo(versionCode=" + this.versionCode + ", versionName=" + this.versionName + ", apkUrl=" + this.apkUrl + ", notes=" + this.notes + ")";
    }

    public UpdateInfo(int i, String versionName, String apkUrl, String notes) {
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        Intrinsics.checkNotNullParameter(apkUrl, "apkUrl");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.versionCode = i;
        this.versionName = versionName;
        this.apkUrl = apkUrl;
        this.notes = notes;
    }

    public final String getApkUrl() {
        return this.apkUrl;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    public final String getVersionName() {
        return this.versionName;
    }
}
