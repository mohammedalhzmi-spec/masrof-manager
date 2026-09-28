package com.mohammedalhzmi.masrofmanager.util;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: AppRoles.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0005"}, m914d2 = {"allows", "", "Lcom/mohammedalhzmi/masrofmanager/util/AppRole;", "permission", "Lcom/mohammedalhzmi/masrofmanager/util/AppPermission;", "app"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class AppRolesKt {

    /* JADX INFO: compiled from: AppRoles.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AppRole.values().length];
            try {
                iArr[AppRole.ADMIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AppRole.FINANCE_MANAGER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AppRole.ACCOUNTANT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AppRole.USER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean allows(AppRole appRole, AppPermission appPermission) {
        int i = WhenMappings.$EnumSwitchMapping$0[appRole.ordinal()];
        if (i == 1) {
            return true;
        }
        if (i == 2) {
            return SetsKt.setOf((Object[]) new AppPermission[]{AppPermission.CREATE_REQUEST, AppPermission.CREATE_ORDER, AppPermission.CREATE_RECEIPT, AppPermission.EDIT, AppPermission.PRINT, AppPermission.BACKUP, AppPermission.APPROVE, AppPermission.REPORTS}).contains(appPermission);
        }
        if (i == 3) {
            return SetsKt.setOf((Object[]) new AppPermission[]{AppPermission.CREATE_REQUEST, AppPermission.CREATE_ORDER, AppPermission.CREATE_RECEIPT, AppPermission.EDIT, AppPermission.PRINT, AppPermission.REPORTS}).contains(appPermission);
        }
        if (i == 4) {
            return SetsKt.setOf((Object[]) new AppPermission[]{AppPermission.CREATE_REQUEST, AppPermission.PRINT}).contains(appPermission);
        }
        throw new NoWhenBranchMatchedException();
    }
}
