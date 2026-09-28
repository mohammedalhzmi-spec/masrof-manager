package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.firebase.firestore.model.Values;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppRoles.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u000eJ\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0005J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/RolePreferences;", "", "<init>", "()V", "FILE", "", "ROLE", "USER_NAME", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "currentRole", "Lcom/mohammedalhzmi/masrofmanager/util/AppRole;", "setRole", "", "role", "userName", "setUserName", Values.VECTOR_MAP_VECTORS_KEY, "can", "", "permission", "Lcom/mohammedalhzmi/masrofmanager/util/AppPermission;", "canCreate", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class RolePreferences {
    public static final int $stable = 0;
    private static final String FILE = "masrof_preferences";
    public static final RolePreferences INSTANCE = new RolePreferences();
    private static final String ROLE = "current_app_role";
    private static final String USER_NAME = "current_user_name";

    /* JADX INFO: compiled from: AppRoles.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DocumentType.values().length];
            try {
                iArr[DocumentType.REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentType.ORDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentType.RECEIPT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DocumentType.RECEIPT_PAPER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private RolePreferences() {
    }

    private final SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(FILE, 0);
    }

    public final AppRole currentRole(Context context) {
        Object objM7781constructorimpl;
        AppRole role;
        Intrinsics.checkNotNullParameter(context, "context");
        AuthenticatedUser current = UserSession.INSTANCE.getCurrent();
        if (current != null && (role = current.getRole()) != null) {
            return role;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            RolePreferences rolePreferences = this;
            String string = prefs(context).getString(ROLE, "ADMIN");
            Intrinsics.checkNotNull(string);
            objM7781constructorimpl = Result.m7781constructorimpl(AppRole.valueOf(string));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        AppRole appRole = AppRole.ADMIN;
        if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
            objM7781constructorimpl = appRole;
        }
        return (AppRole) objM7781constructorimpl;
    }

    public final void setRole(Context context, AppRole role) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(role, "role");
        prefs(context).edit().putString(ROLE, role.name()).apply();
    }

    public final String userName(Context context) {
        String fullName;
        Intrinsics.checkNotNullParameter(context, "context");
        AuthenticatedUser current = UserSession.INSTANCE.getCurrent();
        if (current != null && (fullName = current.getFullName()) != null) {
            return fullName;
        }
        String string = prefs(context).getString(USER_NAME, "المستخدم الرئيسي");
        return string == null ? "" : string;
    }

    public final void setUserName(Context context, String value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(value, "value");
        prefs(context).edit().putString(USER_NAME, value).apply();
    }

    public final boolean can(Context context, AppPermission permission) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(permission, "permission");
        return AppRolesKt.allows(currentRole(context), permission);
    }

    public final boolean canCreate(Context context, DocumentType type) {
        AppPermission appPermission;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            appPermission = AppPermission.CREATE_REQUEST;
        } else if (i == 2) {
            appPermission = AppPermission.CREATE_ORDER;
        } else if (i == 3 || i == 4) {
            appPermission = AppPermission.CREATE_RECEIPT;
        } else {
            appPermission = AppPermission.CREATE_REQUEST;
        }
        return can(context, appPermission);
    }
}
