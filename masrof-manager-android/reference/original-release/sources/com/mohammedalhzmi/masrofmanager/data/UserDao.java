package com.mohammedalhzmi.masrofmanager.data;

import androidx.autofill.HintConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: UserDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H'J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH§@¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\rJ\u0016\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\rJ\u000e\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013¨\u0006\u0014À\u0006\u0003"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/UserDao;", "", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/mohammedalhzmi/masrofmanager/data/UserEntity;", "findActive", HintConstants.AUTOFILL_HINT_USERNAME, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "user", "(Lcom/mohammedalhzmi/masrofmanager/data/UserEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "delete", "count", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public interface UserDao {
    Object count(Continuation<? super Integer> continuation);

    Object delete(UserEntity userEntity, Continuation<? super Unit> continuation);

    Object findActive(String str, Continuation<? super UserEntity> continuation);

    Object insert(UserEntity userEntity, Continuation<? super Long> continuation);

    Flow<List<UserEntity>> observeAll();

    Object update(UserEntity userEntity, Continuation<? super Unit> continuation);
}
