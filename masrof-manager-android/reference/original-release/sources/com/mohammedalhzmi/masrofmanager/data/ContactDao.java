package com.mohammedalhzmi.masrofmanager.data;

import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: ContactDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H'J\u001c\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\f¨\u0006\u000eÀ\u0006\u0003"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/ContactDao;", "", "getAllContacts", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/mohammedalhzmi/masrofmanager/data/ContactEntity;", "getContactsByType", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/ContactType;", "insertContact", "", "contact", "(Lcom/mohammedalhzmi/masrofmanager/data/ContactEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteContact", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public interface ContactDao {
    Object deleteContact(ContactEntity contactEntity, Continuation<? super Unit> continuation);

    Flow<List<ContactEntity>> getAllContacts();

    Flow<List<ContactEntity>> getContactsByType(ContactType type);

    Object insertContact(ContactEntity contactEntity, Continuation<? super Unit> continuation);
}
