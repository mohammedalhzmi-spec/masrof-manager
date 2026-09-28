package com.mohammedalhzmi.masrofmanager.data;

import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: DesignDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\b2\u0006\u0010\u0012\u001a\u00020\nH'J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\nH§@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\nH§@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0011H§@¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0011H§@¢\u0006\u0002\u0010\u0018J\u0016\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0011H§@¢\u0006\u0002\u0010\u0018J\u001e\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001eH§@¢\u0006\u0002\u0010\u001f¨\u0006 À\u0006\u0003"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/DesignDao;", "", "getDesign", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;", LinkHeader.Parameters.Type, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeDesign", "Lkotlinx/coroutines/flow/Flow;", "upsertDesign", "", "design", "(Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDesign", "", "observeElements", "", "Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "designId", "getElements", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteElements", "insertElement", "element", "(Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateElement", "deleteElement", "setLayer", "elementId", "zIndex", "", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public interface DesignDao {
    Object deleteDesign(String str, Continuation<? super Unit> continuation);

    Object deleteElement(DesignElementEntity designElementEntity, Continuation<? super Unit> continuation);

    Object deleteElements(long j, Continuation<? super Unit> continuation);

    Object getDesign(String str, Continuation<? super DocumentDesignEntity> continuation);

    Object getElements(long j, Continuation<? super List<DesignElementEntity>> continuation);

    Object insertElement(DesignElementEntity designElementEntity, Continuation<? super Long> continuation);

    Flow<DocumentDesignEntity> observeDesign(String type);

    Flow<List<DesignElementEntity>> observeElements(long designId);

    Object setLayer(long j, int i, Continuation<? super Unit> continuation);

    Object updateElement(DesignElementEntity designElementEntity, Continuation<? super Unit> continuation);

    Object upsertDesign(DocumentDesignEntity documentDesignEntity, Continuation<? super Long> continuation);
}
