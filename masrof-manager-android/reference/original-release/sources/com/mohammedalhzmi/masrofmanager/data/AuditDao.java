package com.mohammedalhzmi.masrofmanager.data;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: UserDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H'J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\t¨\u0006\nÀ\u0006\u0003"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/AuditDao;", "", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/mohammedalhzmi/masrofmanager/data/AuditLogEntity;", "insert", "", "log", "(Lcom/mohammedalhzmi/masrofmanager/data/AuditLogEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public interface AuditDao {
    Object insert(AuditLogEntity auditLogEntity, Continuation<? super Unit> continuation);

    Flow<List<AuditLogEntity>> observeAll();
}
