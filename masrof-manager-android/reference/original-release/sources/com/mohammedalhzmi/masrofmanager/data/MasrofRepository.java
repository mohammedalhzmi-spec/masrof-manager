package com.mohammedalhzmi.masrofmanager.data;

import androidx.autofill.HintConstants;
import com.google.android.gms.common.Scopes;
import io.ktor.http.LinkHeader;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: MasrofRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0007\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0011J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010\u001dJ\u0016\u0010 \u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010\u001dJ\u001c\u0010!\u001a\u00020\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0086@¢\u0006\u0002\u0010#J\u001e\u0010$\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u001bH\u0086@¢\u0006\u0002\u0010&J\u001e\u0010'\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u001bH\u0086@¢\u0006\u0002\u0010&J\u001e\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001bH\u0086@¢\u0006\u0002\u0010*J\u000e\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010,0\u0011J\u0016\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020,H\u0086@¢\u0006\u0002\u0010/J\u0012\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002010\u00120\u0011J\u001a\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002010\u00120\u00112\u0006\u00103\u001a\u000204J\u0016\u00105\u001a\u00020\u001f2\u0006\u00106\u001a\u000201H\u0086@¢\u0006\u0002\u00107J\u0016\u00108\u001a\u00020\u001f2\u0006\u00106\u001a\u000201H\u0086@¢\u0006\u0002\u00107J\u0018\u0010?\u001a\u0004\u0018\u00010:2\u0006\u0010@\u001a\u00020AH\u0086@¢\u0006\u0002\u0010BJ\u0016\u0010C\u001a\u00020\u001b2\u0006\u0010D\u001a\u00020:H\u0086@¢\u0006\u0002\u0010EJ\u0016\u0010F\u001a\u00020\u001f2\u0006\u0010D\u001a\u00020:H\u0086@¢\u0006\u0002\u0010EJ\u0016\u0010G\u001a\u00020\u001f2\u0006\u0010D\u001a\u00020:H\u0086@¢\u0006\u0002\u0010EJ\u000e\u0010H\u001a\u00020\u0019H\u0086@¢\u0006\u0002\u0010IJ\u0016\u0010J\u001a\u00020\u001f2\u0006\u0010K\u001a\u00020=H\u0086@¢\u0006\u0002\u0010LJ\u0018\u0010M\u001a\u0004\u0018\u00010N2\u0006\u00103\u001a\u00020OH\u0086@¢\u0006\u0002\u0010PJ\u001a\u0010Q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020R0\u00120\u00112\u0006\u0010S\u001a\u00020\u001bJ\u001c\u0010T\u001a\b\u0012\u0004\u0012\u00020R0\u00122\u0006\u0010S\u001a\u00020\u001bH\u0086@¢\u0006\u0002\u0010UJ$\u0010V\u001a\u00020\u001f2\u0006\u0010S\u001a\u00020\u001b2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020R0\u0012H\u0086@¢\u0006\u0002\u0010XJ\u0016\u0010Y\u001a\u00020\u001b2\u0006\u0010Z\u001a\u00020NH\u0086@¢\u0006\u0002\u0010[J\u0016\u0010\\\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020RH\u0086@¢\u0006\u0002\u0010^J\u0016\u0010_\u001a\u00020\u001f2\u0006\u0010]\u001a\u00020RH\u0086@¢\u0006\u0002\u0010^J\u0016\u0010`\u001a\u00020\u001f2\u0006\u0010]\u001a\u00020RH\u0086@¢\u0006\u0002\u0010^J\u001e\u0010a\u001a\u00020\u001f2\u0006\u0010b\u001a\u00020\u001b2\u0006\u0010c\u001a\u00020\u0019H\u0086@¢\u0006\u0002\u0010dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u001d\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020:0\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0015R\u001d\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0015¨\u0006e"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/MasrofRepository;", "", "documentDao", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDao;", "settingsDao", "Lcom/mohammedalhzmi/masrofmanager/data/SettingsDao;", "contactDao", "Lcom/mohammedalhzmi/masrofmanager/data/ContactDao;", "userDao", "Lcom/mohammedalhzmi/masrofmanager/data/UserDao;", "auditDao", "Lcom/mohammedalhzmi/masrofmanager/data/AuditDao;", "designDao", "Lcom/mohammedalhzmi/masrofmanager/data/DesignDao;", "<init>", "(Lcom/mohammedalhzmi/masrofmanager/data/DocumentDao;Lcom/mohammedalhzmi/masrofmanager/data/SettingsDao;Lcom/mohammedalhzmi/masrofmanager/data/ContactDao;Lcom/mohammedalhzmi/masrofmanager/data/UserDao;Lcom/mohammedalhzmi/masrofmanager/data/AuditDao;Lcom/mohammedalhzmi/masrofmanager/data/DesignDao;)V", "allDocuments", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "getAllDocuments", "()Lkotlinx/coroutines/flow/Flow;", "archivedDocuments", "getArchivedDocuments", "getLastDocumentNumber", "", "insert", "", "document", "(Lcom/mohammedalhzmi/masrofmanager/data/Document;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "delete", "replaceRemoteDocuments", "documents", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "archive", "timestamp", "(Lcom/mohammedalhzmi/masrofmanager/data/Document;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restore", "archiveOlderThan", "cutoff", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrganizationProfile", "Lcom/mohammedalhzmi/masrofmanager/data/OrganizationProfile;", "saveOrganizationProfile", Scopes.PROFILE, "(Lcom/mohammedalhzmi/masrofmanager/data/OrganizationProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllContacts", "Lcom/mohammedalhzmi/masrofmanager/data/ContactEntity;", "getContactsByType", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/ContactType;", "insertContact", "contact", "(Lcom/mohammedalhzmi/masrofmanager/data/ContactEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteContact", "allUsers", "Lcom/mohammedalhzmi/masrofmanager/data/UserEntity;", "getAllUsers", "auditLogs", "Lcom/mohammedalhzmi/masrofmanager/data/AuditLogEntity;", "getAuditLogs", "findActiveUser", HintConstants.AUTOFILL_HINT_USERNAME, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertUser", "user", "(Lcom/mohammedalhzmi/masrofmanager/data/UserEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUser", "deleteUser", "userCount", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addAudit", "log", "(Lcom/mohammedalhzmi/masrofmanager/data/AuditLogEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDesign", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "(Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeDesignElements", "Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "designId", "designElements", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "replaceDesignElements", "elements", "(JLjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDesign", "design", "(Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addDesignElement", "element", "(Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateDesignElement", "deleteDesignElement", "setDesignLayer", "elementId", "zIndex", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class MasrofRepository {
    public static final int $stable = 8;
    private final Flow<List<Document>> allDocuments;
    private final Flow<List<UserEntity>> allUsers;
    private final Flow<List<Document>> archivedDocuments;
    private final AuditDao auditDao;
    private final Flow<List<AuditLogEntity>> auditLogs;
    private final ContactDao contactDao;
    private final DesignDao designDao;
    private final DocumentDao documentDao;
    private final SettingsDao settingsDao;
    private final UserDao userDao;

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.data.MasrofRepository$replaceDesignElements$1 */
    /* JADX INFO: compiled from: MasrofRepository.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.data.MasrofRepository", m938f = "MasrofRepository.kt", m939i = {0, 0, 1, 1, 1, 1, 1, 1, 1}, m940l = {50, 50}, m941m = "replaceDesignElements", m942n = {"elements", "designId", "elements", "$this$forEach$iv", "element$iv", "it", "designId", "$i$f$forEach", "$i$a$-forEach-MasrofRepository$replaceDesignElements$2"}, m943s = {"L$0", "J$0", "L$0", "L$1", "L$3", "L$4", "J$0", "I$0", "I$1"})
    static final class C38651 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C38651(Continuation<? super C38651> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MasrofRepository.this.replaceDesignElements(0L, null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.data.MasrofRepository$replaceRemoteDocuments$1 */
    /* JADX INFO: compiled from: MasrofRepository.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.data.MasrofRepository", m938f = "MasrofRepository.kt", m939i = {0, 1, 1, 1, 1, 1, 1}, m940l = {21, 22}, m941m = "replaceRemoteDocuments", m942n = {"documents", "documents", "$this$forEach$iv", "element$iv", "it", "$i$f$forEach", "$i$a$-forEach-MasrofRepository$replaceRemoteDocuments$2"}, m943s = {"L$0", "L$0", "L$1", "L$3", "L$4", "I$0", "I$1"})
    static final class C38661 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C38661(Continuation<? super C38661> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MasrofRepository.this.replaceRemoteDocuments(null, this);
        }
    }

    public MasrofRepository(DocumentDao documentDao, SettingsDao settingsDao, ContactDao contactDao, UserDao userDao, AuditDao auditDao, DesignDao designDao) {
        Intrinsics.checkNotNullParameter(documentDao, "documentDao");
        Intrinsics.checkNotNullParameter(settingsDao, "settingsDao");
        Intrinsics.checkNotNullParameter(contactDao, "contactDao");
        Intrinsics.checkNotNullParameter(userDao, "userDao");
        Intrinsics.checkNotNullParameter(auditDao, "auditDao");
        Intrinsics.checkNotNullParameter(designDao, "designDao");
        this.documentDao = documentDao;
        this.settingsDao = settingsDao;
        this.contactDao = contactDao;
        this.userDao = userDao;
        this.auditDao = auditDao;
        this.designDao = designDao;
        this.allDocuments = documentDao.getAllDocuments();
        this.archivedDocuments = documentDao.getArchivedDocuments();
        this.allUsers = userDao.observeAll();
        this.auditLogs = auditDao.observeAll();
    }

    public final Flow<List<Document>> getAllDocuments() {
        return this.allDocuments;
    }

    public final Flow<List<Document>> getArchivedDocuments() {
        return this.archivedDocuments;
    }

    public final Flow<Integer> getLastDocumentNumber() {
        return this.documentDao.getLastDocumentNumber();
    }

    public final Object insert(Document document, Continuation<? super Long> continuation) {
        return this.documentDao.insert(document, continuation);
    }

    public final Object update(Document document, Continuation<? super Unit> continuation) {
        Object objUpdate = this.documentDao.update(document, continuation);
        return objUpdate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objUpdate : Unit.INSTANCE;
    }

    public final Object delete(Document document, Continuation<? super Unit> continuation) {
        Object objDelete = this.documentDao.delete(document, continuation);
        return objDelete == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDelete : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0074  */
    /* JADX WARN: Code duplicated, block: B:28:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:? A[LOOP:0: B:20:0x006e->B:30:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0061, code lost:
    
        if (r12.deleteAll(r0) == r1) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object replaceRemoteDocuments(List<Document> list, Continuation<? super Unit> continuation) {
        C38661 c38661;
        Iterator it;
        List<Document> list2;
        Iterable iterable;
        int i;
        Document document;
        DocumentDao documentDao;
        if (continuation instanceof C38661) {
            c38661 = (C38661) continuation;
            if ((c38661.label & Integer.MIN_VALUE) != 0) {
                c38661.label -= Integer.MIN_VALUE;
            } else {
                c38661 = new C38661(continuation);
            }
        } else {
            c38661 = new C38661(continuation);
        }
        Object obj = c38661.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c38661.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            DocumentDao documentDao2 = this.documentDao;
            c38661.L$0 = list;
            c38661.label = 1;
        } else {
            if (i2 == 1) {
                list = (List) c38661.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i3 = c38661.I$1;
                i = c38661.I$0;
                Object obj2 = c38661.L$3;
                it = (Iterator) c38661.L$2;
                iterable = (Iterable) c38661.L$1;
                list2 = (List) c38661.L$0;
                ResultKt.throwOnFailure(obj);
            }
            while (it.hasNext()) {
                Object next = it.next();
                document = (Document) next;
                documentDao = this.documentDao;
                c38661.L$0 = SpillingKt.nullOutSpilledVariable(list2);
                c38661.L$1 = SpillingKt.nullOutSpilledVariable(iterable);
                c38661.L$2 = it;
                c38661.L$3 = SpillingKt.nullOutSpilledVariable(next);
                c38661.L$4 = SpillingKt.nullOutSpilledVariable(document);
                c38661.I$0 = i;
                c38661.I$1 = 0;
                c38661.label = 2;
                if (documentDao.insert(document, c38661) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        List<Document> list3 = list;
        it = list3.iterator();
        list2 = list;
        iterable = list3;
        i = 0;
        while (it.hasNext()) {
            Object next2 = it.next();
            document = (Document) next2;
            documentDao = this.documentDao;
            c38661.L$0 = SpillingKt.nullOutSpilledVariable(list2);
            c38661.L$1 = SpillingKt.nullOutSpilledVariable(iterable);
            c38661.L$2 = it;
            c38661.L$3 = SpillingKt.nullOutSpilledVariable(next2);
            c38661.L$4 = SpillingKt.nullOutSpilledVariable(document);
            c38661.I$0 = i;
            c38661.I$1 = 0;
            c38661.label = 2;
            if (documentDao.insert(document, c38661) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    public final Object archive(Document document, long j, Continuation<? super Unit> continuation) {
        Object objArchive = this.documentDao.archive(document.getId(), j, continuation);
        return objArchive == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objArchive : Unit.INSTANCE;
    }

    public final Object restore(Document document, long j, Continuation<? super Unit> continuation) {
        Object objRestore = this.documentDao.restore(document.getId(), j, continuation);
        return objRestore == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRestore : Unit.INSTANCE;
    }

    public final Object archiveOlderThan(long j, long j2, Continuation<? super Unit> continuation) {
        Object objArchiveOlderThan = this.documentDao.archiveOlderThan(j, j2, continuation);
        return objArchiveOlderThan == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objArchiveOlderThan : Unit.INSTANCE;
    }

    public final Flow<OrganizationProfile> getOrganizationProfile() {
        return this.settingsDao.getOrganizationProfile();
    }

    public final Object saveOrganizationProfile(OrganizationProfile organizationProfile, Continuation<? super Unit> continuation) {
        Object objSaveOrganizationProfile = this.settingsDao.saveOrganizationProfile(organizationProfile, continuation);
        return objSaveOrganizationProfile == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSaveOrganizationProfile : Unit.INSTANCE;
    }

    public final Flow<List<ContactEntity>> getAllContacts() {
        return this.contactDao.getAllContacts();
    }

    public final Flow<List<ContactEntity>> getContactsByType(ContactType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return this.contactDao.getContactsByType(type);
    }

    public final Object insertContact(ContactEntity contactEntity, Continuation<? super Unit> continuation) {
        Object objInsertContact = this.contactDao.insertContact(contactEntity, continuation);
        return objInsertContact == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInsertContact : Unit.INSTANCE;
    }

    public final Object deleteContact(ContactEntity contactEntity, Continuation<? super Unit> continuation) {
        Object objDeleteContact = this.contactDao.deleteContact(contactEntity, continuation);
        return objDeleteContact == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteContact : Unit.INSTANCE;
    }

    public final Flow<List<UserEntity>> getAllUsers() {
        return this.allUsers;
    }

    public final Flow<List<AuditLogEntity>> getAuditLogs() {
        return this.auditLogs;
    }

    public final Object findActiveUser(String str, Continuation<? super UserEntity> continuation) {
        return this.userDao.findActive(str, continuation);
    }

    public final Object insertUser(UserEntity userEntity, Continuation<? super Long> continuation) {
        return this.userDao.insert(userEntity, continuation);
    }

    public final Object updateUser(UserEntity userEntity, Continuation<? super Unit> continuation) {
        Object objUpdate = this.userDao.update(userEntity, continuation);
        return objUpdate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objUpdate : Unit.INSTANCE;
    }

    public final Object deleteUser(UserEntity userEntity, Continuation<? super Unit> continuation) {
        Object objDelete = this.userDao.delete(userEntity, continuation);
        return objDelete == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDelete : Unit.INSTANCE;
    }

    public final Object userCount(Continuation<? super Integer> continuation) {
        return this.userDao.count(continuation);
    }

    public final Object addAudit(AuditLogEntity auditLogEntity, Continuation<? super Unit> continuation) {
        Object objInsert = this.auditDao.insert(auditLogEntity, continuation);
        return objInsert == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInsert : Unit.INSTANCE;
    }

    public final Object getDesign(DocumentType documentType, Continuation<? super DocumentDesignEntity> continuation) {
        return this.designDao.getDesign(documentType.name(), continuation);
    }

    public final Flow<List<DesignElementEntity>> observeDesignElements(long designId) {
        return this.designDao.observeElements(designId);
    }

    public final Object designElements(long j, Continuation<? super List<DesignElementEntity>> continuation) {
        return this.designDao.getElements(j, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0088  */
    /* JADX WARN: Code duplicated, block: B:28:0x0124 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:? A[LOOP:0: B:20:0x0082->B:30:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0071, code lost:
    
        if (r3.deleteElements(r1, r4) == r5) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object replaceDesignElements(long j, List<DesignElementEntity> list, Continuation<? super Unit> continuation) {
        C38651 c38651;
        List<DesignElementEntity> list2;
        Iterable iterable;
        List<DesignElementEntity> list3;
        long j2;
        int i;
        Iterator it;
        DesignDao designDao;
        DesignElementEntity designElementEntityCopy$default;
        long j3 = j;
        if (continuation instanceof C38651) {
            c38651 = (C38651) continuation;
            if ((c38651.label & Integer.MIN_VALUE) != 0) {
                c38651.label -= Integer.MIN_VALUE;
            } else {
                c38651 = new C38651(continuation);
            }
        } else {
            c38651 = new C38651(continuation);
        }
        Object obj = c38651.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c38651.label;
        if (i2 != 0) {
            if (i2 == 1) {
                j3 = c38651.J$0;
                list2 = (List) c38651.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i3 = c38651.I$1;
                i = c38651.I$0;
                long j4 = c38651.J$0;
                Object obj2 = c38651.L$3;
                it = (Iterator) c38651.L$2;
                iterable = (Iterable) c38651.L$1;
                List<DesignElementEntity> list4 = (List) c38651.L$0;
                ResultKt.throwOnFailure(obj);
                j2 = j4;
                list3 = list4;
            }
            while (it.hasNext()) {
                Object next = it.next();
                DesignElementEntity designElementEntity = (DesignElementEntity) next;
                designDao = this.designDao;
                designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntity, 0L, j2, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554428, null);
                c38651.L$0 = SpillingKt.nullOutSpilledVariable(list3);
                c38651.L$1 = SpillingKt.nullOutSpilledVariable(iterable);
                c38651.L$2 = it;
                c38651.L$3 = SpillingKt.nullOutSpilledVariable(next);
                c38651.L$4 = SpillingKt.nullOutSpilledVariable(designElementEntity);
                c38651.J$0 = j2;
                c38651.I$0 = i;
                c38651.I$1 = 0;
                c38651.label = 2;
                if (designDao.insertElement(designElementEntityCopy$default, c38651) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        DesignDao designDao2 = this.designDao;
        list2 = list;
        c38651.L$0 = list2;
        c38651.J$0 = j3;
        c38651.label = 1;
        List<DesignElementEntity> list5 = list2;
        Iterator it2 = list5.iterator();
        List<DesignElementEntity> list6 = list2;
        iterable = list5;
        list3 = list6;
        j2 = j3;
        i = 0;
        it = it2;
        while (it.hasNext()) {
            Object next2 = it.next();
            DesignElementEntity designElementEntity2 = (DesignElementEntity) next2;
            designDao = this.designDao;
            designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntity2, 0L, j2, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554428, null);
            c38651.L$0 = SpillingKt.nullOutSpilledVariable(list3);
            c38651.L$1 = SpillingKt.nullOutSpilledVariable(iterable);
            c38651.L$2 = it;
            c38651.L$3 = SpillingKt.nullOutSpilledVariable(next2);
            c38651.L$4 = SpillingKt.nullOutSpilledVariable(designElementEntity2);
            c38651.J$0 = j2;
            c38651.I$0 = i;
            c38651.I$1 = 0;
            c38651.label = 2;
            if (designDao.insertElement(designElementEntityCopy$default, c38651) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    public final Object saveDesign(DocumentDesignEntity documentDesignEntity, Continuation<? super Long> continuation) {
        return this.designDao.upsertDesign(documentDesignEntity, continuation);
    }

    public final Object addDesignElement(DesignElementEntity designElementEntity, Continuation<? super Long> continuation) {
        return this.designDao.insertElement(designElementEntity, continuation);
    }

    public final Object updateDesignElement(DesignElementEntity designElementEntity, Continuation<? super Unit> continuation) {
        Object objUpdateElement = this.designDao.updateElement(designElementEntity, continuation);
        return objUpdateElement == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objUpdateElement : Unit.INSTANCE;
    }

    public final Object deleteDesignElement(DesignElementEntity designElementEntity, Continuation<? super Unit> continuation) {
        Object objDeleteElement = this.designDao.deleteElement(designElementEntity, continuation);
        return objDeleteElement == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeleteElement : Unit.INSTANCE;
    }

    public final Object setDesignLayer(long j, int i, Continuation<? super Unit> continuation) {
        Object layer = this.designDao.setLayer(j, i, continuation);
        return layer == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? layer : Unit.INSTANCE;
    }
}
