package com.mohammedalhzmi.masrofmanager.data;

import com.google.android.gms.common.Scopes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: SettingsDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H'J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H§@¢\u0006\u0002\u0010\b¨\u0006\tÀ\u0006\u0003"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/SettingsDao;", "", "getOrganizationProfile", "Lkotlinx/coroutines/flow/Flow;", "Lcom/mohammedalhzmi/masrofmanager/data/OrganizationProfile;", "saveOrganizationProfile", "", Scopes.PROFILE, "(Lcom/mohammedalhzmi/masrofmanager/data/OrganizationProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public interface SettingsDao {
    Flow<OrganizationProfile> getOrganizationProfile();

    Object saveOrganizationProfile(OrganizationProfile organizationProfile, Continuation<? super Unit> continuation);
}
