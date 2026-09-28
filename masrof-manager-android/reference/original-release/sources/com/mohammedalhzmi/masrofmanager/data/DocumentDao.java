package com.mohammedalhzmi.masrofmanager.data;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: DocumentDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H'J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H'J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH§@¢\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH§@¢\u0006\u0002\u0010\fJ\u001e\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH§@¢\u0006\u0002\u0010\fJ\u0016\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0015\u001a\u00020\bH§@¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0003H'¨\u0006\u0019À\u0006\u0003"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/DocumentDao;", "", "getAllDocuments", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "getArchivedDocuments", "archive", "", "id", "", "timestamp", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restore", "archiveOlderThan", "cutoff", "insert", "document", "(Lcom/mohammedalhzmi/masrofmanager/data/Document;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "delete", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastDocumentNumber", "", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public interface DocumentDao {
    Object archive(long j, long j2, Continuation<? super Unit> continuation);

    Object archiveOlderThan(long j, long j2, Continuation<? super Unit> continuation);

    Object delete(Document document, Continuation<? super Unit> continuation);

    Object deleteAll(Continuation<? super Unit> continuation);

    Flow<List<Document>> getAllDocuments();

    Flow<List<Document>> getArchivedDocuments();

    Flow<Integer> getLastDocumentNumber();

    Object insert(Document document, Continuation<? super Long> continuation);

    Object restore(long j, long j2, Continuation<? super Unit> continuation);

    Object update(Document document, Continuation<? super Unit> continuation);
}
