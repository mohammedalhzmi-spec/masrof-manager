package com.mohammedalhzmi.masrofmanager.p010ui;

import android.content.Context;
import android.net.Uri;
import androidx.autofill.HintConstants;
import androidx.compose.runtime.ComposerKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.fido.u2f.api.common.RegisterRequest;
import com.google.api.Endpoint;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.model.Values;
import com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync;
import com.mohammedalhzmi.masrofmanager.data.AuditLogEntity;
import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity;
import com.mohammedalhzmi.masrofmanager.data.Document;
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity;
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import com.mohammedalhzmi.masrofmanager.data.MasrofDatabase;
import com.mohammedalhzmi.masrofmanager.data.MasrofRepository;
import com.mohammedalhzmi.masrofmanager.data.ModelsKt;
import com.mohammedalhzmi.masrofmanager.data.UserEntity;
import com.mohammedalhzmi.masrofmanager.util.AiLayoutAssistant;
import com.mohammedalhzmi.masrofmanager.util.AppBackupManager;
import com.mohammedalhzmi.masrofmanager.util.AppRole;
import com.mohammedalhzmi.masrofmanager.util.AuthSecurity;
import com.mohammedalhzmi.masrofmanager.util.AuthenticatedUser;
import com.mohammedalhzmi.masrofmanager.util.RememberedLogin;
import com.mohammedalhzmi.masrofmanager.util.UserSession;
import io.ktor.http.LinkHeader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p012io.ByteStreamsKt;
import kotlin.p012io.CloseableKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: MasrofViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u00108\u001a\b\u0012\u0004\u0012\u00020(0\u0017H\u0002J\b\u00109\u001a\u00020:H\u0002J\u000e\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020=J\u000e\u0010>\u001a\u00020:2\u0006\u0010?\u001a\u00020,J\u0014\u0010@\u001a\u00020:2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\u0017J\u000e\u0010C\u001a\u00020:2\u0006\u0010D\u001a\u00020(J\u000e\u0010E\u001a\u00020:2\u0006\u0010D\u001a\u00020(J\u000e\u0010F\u001a\u00020:2\u0006\u0010D\u001a\u00020(J\u0016\u0010G\u001a\u00020:2\u0006\u0010D\u001a\u00020(2\u0006\u0010H\u001a\u00020\u001eJ\u000e\u0010I\u001a\u00020:2\u0006\u0010D\u001a\u00020(J\u0006\u0010J\u001a\u00020:J\u0006\u0010K\u001a\u00020:J\u0006\u0010L\u001a\u00020:J\u000e\u0010M\u001a\u00020:H\u0086@¢\u0006\u0002\u0010NJ\u0018\u0010O\u001a\u0004\u0018\u00010P2\u0006\u0010Q\u001a\u00020RH\u0086@¢\u0006\u0002\u0010SJ\u0018\u0010T\u001a\u0004\u0018\u00010P2\u0006\u0010Q\u001a\u00020RH\u0086@¢\u0006\u0002\u0010SJ0\u0010U\u001a\u0004\u0018\u00010P2\u0006\u0010Q\u001a\u00020R2\u0006\u0010V\u001a\u00020\u000b2\u0006\u0010W\u001a\u00020\u000b2\u0006\u0010X\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010YJ\u001e\u0010Z\u001a\u00020:2\u0006\u0010[\u001a\u00020P2\u0006\u0010W\u001a\u00020\u000bH\u0082@¢\u0006\u0002\u0010\\J(\u0010]\u001a\u0004\u0018\u00010P2\u0006\u0010Q\u001a\u00020R2\u0006\u0010^\u001a\u00020\u000b2\u0006\u0010W\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010_J&\u0010`\u001a\u00020\u00112\u0006\u0010Q\u001a\u00020R2\u0006\u0010V\u001a\u00020\u000b2\u0006\u0010X\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010aJ.\u0010b\u001a\u00020\u00112\u0006\u0010V\u001a\u00020\u000b2\u0006\u0010W\u001a\u00020\u000b2\u0006\u0010c\u001a\u00020\u000b2\u0006\u0010d\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010eJ\u0012\u0010f\u001a\u00020g2\b\u0010h\u001a\u0004\u0018\u00010\u000bH\u0002J&\u0010i\u001a\u00020:2\u0006\u0010V\u001a\u00020\u000b2\u0006\u0010W\u001a\u00020\u000b2\u0006\u0010c\u001a\u00020\u000b2\u0006\u0010d\u001a\u00020\u000bJ0\u0010j\u001a\u00020:2\u0006\u0010k\u001a\u00020!2\b\u0010W\u001a\u0004\u0018\u00010\u000b2\u0006\u0010c\u001a\u00020\u000b2\u0006\u0010d\u001a\u00020\u000b2\u0006\u0010l\u001a\u00020\u0011J\u000e\u0010m\u001a\u00020:2\u0006\u0010k\u001a\u00020!J\u0016\u0010n\u001a\u00020:2\u0006\u0010o\u001a\u00020\u000b2\u0006\u0010p\u001a\u00020\u000bJ\u001e\u0010q\u001a\u00020:2\u0006\u0010o\u001a\u00020\u000b2\u0006\u0010p\u001a\u00020\u000bH\u0082@¢\u0006\u0002\u0010rJ\u000e\u0010s\u001a\u00020:2\u0006\u0010t\u001a\u00020\u0018J\u0016\u0010u\u001a\u00020:2\u0006\u0010t\u001a\u00020\u00182\u0006\u0010v\u001a\u00020wJ\u000e\u0010x\u001a\u00020:2\u0006\u0010t\u001a\u00020\u0018J\u000e\u0010y\u001a\u00020:2\u0006\u0010t\u001a\u00020\u0018J\u000e\u0010z\u001a\u00020:2\u0006\u0010t\u001a\u00020\u0018J\u000e\u0010{\u001a\u00020:2\u0006\u0010t\u001a\u00020\u0018J\u0016\u0010|\u001a\u00020:2\u0006\u0010Q\u001a\u00020R2\u0006\u0010}\u001a\u00020~J\u0016\u0010\u007f\u001a\u00020:2\u0006\u0010Q\u001a\u00020R2\u0006\u0010}\u001a\u00020~J\u0017\u0010\u0080\u0001\u001a\u00020:2\u0006\u0010Q\u001a\u00020R2\u0006\u0010}\u001a\u00020~J\u0017\u0010\u0081\u0001\u001a\u00020:2\u0006\u0010Q\u001a\u00020R2\u0006\u0010}\u001a\u00020~R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u001d\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\u00170'¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0019\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010,0'¢\u0006\b\n\u0000\u001a\u0004\b-\u0010*R\u000e\u0010.\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\u001701X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\u001701X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u00103\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107¨\u0006\u0082\u0001"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/mohammedalhzmi/masrofmanager/data/MasrofRepository;", "database", "Lcom/mohammedalhzmi/masrofmanager/data/MasrofDatabase;", "cloud", "Lcom/mohammedalhzmi/masrofmanager/cloud/FirebaseCloudSync;", "<init>", "(Lcom/mohammedalhzmi/masrofmanager/data/MasrofRepository;Lcom/mohammedalhzmi/masrofmanager/data/MasrofDatabase;Lcom/mohammedalhzmi/masrofmanager/cloud/FirebaseCloudSync;)V", "lastAuthError", "", "getLastAuthError", "()Ljava/lang/String;", "setLastAuthError", "(Ljava/lang/String;)V", "isManagerSession", "", "()Z", "setManagerSession", "(Z)V", "allDocuments", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "getAllDocuments", "()Lkotlinx/coroutines/flow/StateFlow;", "archivedDocuments", "getArchivedDocuments", "lastDocumentNumber", "", "getLastDocumentNumber", "allUsers", "Lcom/mohammedalhzmi/masrofmanager/data/UserEntity;", "getAllUsers", "auditLogs", "Lcom/mohammedalhzmi/masrofmanager/data/AuditLogEntity;", "getAuditLogs", "designElements", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "getDesignElements", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "activeDesign", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;", "getActiveDesign", "activeDesignId", "", "undoStack", "Lkotlin/collections/ArrayDeque;", "redoStack", "clipboard", "getClipboard", "()Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "setClipboard", "(Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;)V", "snapshot", "rememberChange", "", "loadDesign", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "updateDesign", "design", "applyAiCommands", "commands", "Lcom/mohammedalhzmi/masrofmanager/util/AiLayoutAssistant$Command;", "addDesignElement", "element", "updateDesignElement", "deleteDesignElement", "moveLayer", "delta", "copyElement", "pasteElement", "undo", "redo", "ensureDefaultAdmin", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreRememberedUser", "Lcom/mohammedalhzmi/masrofmanager/util/AuthenticatedUser;", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreLocalUser", "authenticate", HintConstants.AUTOFILL_HINT_USERNAME, "password", "remember", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cacheLocalUser", "auth", "(Lcom/mohammedalhzmi/masrofmanager/util/AuthenticatedUser;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authenticateFirstLogin", "email", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeFirstLoginUsername", "(Landroid/content/Context;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerUser", "fullName", "role", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "roleFrom", "Lcom/mohammedalhzmi/masrofmanager/util/AppRole;", Values.VECTOR_MAP_VECTORS_KEY, "createUser", "updateUser", "user", "active", "deleteUser", "recordAudit", "action", "details", "audit", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addDocument", "document", "transitionDocument", "target", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentStatus;", "updateDocument", "deleteDocument", "archiveDocument", "restoreDocument", "exportDatabase", "uri", "Landroid/net/Uri;", "importDatabase", "exportFullBackup", "importFullBackup", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class MasrofViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<DocumentDesignEntity> activeDesign;
    private long activeDesignId;
    private final StateFlow<List<Document>> allDocuments;
    private final StateFlow<List<UserEntity>> allUsers;
    private final StateFlow<List<Document>> archivedDocuments;
    private final StateFlow<List<AuditLogEntity>> auditLogs;
    private DesignElementEntity clipboard;
    private final FirebaseCloudSync cloud;
    private final MasrofDatabase database;
    private final MutableStateFlow<List<DesignElementEntity>> designElements;
    private boolean isManagerSession;
    private String lastAuthError;
    private final StateFlow<Integer> lastDocumentNumber;
    private final ArrayDeque<List<DesignElementEntity>> redoStack;
    private final MasrofRepository repository;
    private final ArrayDeque<List<DesignElementEntity>> undoStack;

    /* JADX INFO: compiled from: MasrofViewModel.kt */
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
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$authenticate$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel", m938f = "MasrofViewModel.kt", m939i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8}, m940l = {162, 169, 172, 173, 179, 185, 187, 188, 192}, m941m = "authenticate", m942n = {"context", HintConstants.AUTOFILL_HINT_USERNAME, "password", "$this$authenticate_u24lambda_u245", "remember", "$i$a$-runCatching-MasrofViewModel$authenticate$2", "context", HintConstants.AUTOFILL_HINT_USERNAME, "password", "$this$authenticate_u24lambda_u245", ImagesContract.LOCAL, "auth", "remember", "$i$a$-runCatching-MasrofViewModel$authenticate$2", "context", HintConstants.AUTOFILL_HINT_USERNAME, "password", "$this$authenticate_u24lambda_u245", ImagesContract.LOCAL, "remember", "$i$a$-runCatching-MasrofViewModel$authenticate$2", "context", HintConstants.AUTOFILL_HINT_USERNAME, "password", "$this$authenticate_u24lambda_u245", ImagesContract.LOCAL, "uid", "remember", "$i$a$-runCatching-MasrofViewModel$authenticate$2", "context", HintConstants.AUTOFILL_HINT_USERNAME, "password", "$this$authenticate_u24lambda_u245", ImagesContract.LOCAL, "uid", "access", "remember", "$i$a$-runCatching-MasrofViewModel$authenticate$2", "context", HintConstants.AUTOFILL_HINT_USERNAME, "password", "$this$authenticate_u24lambda_u245", ImagesContract.LOCAL, "uid", "access", Scopes.PROFILE, "displayName", "role", "email", "auth", "remember", "$i$a$-runCatching-MasrofViewModel$authenticate$2", "context", HintConstants.AUTOFILL_HINT_USERNAME, "password", "$this$authenticate_u24lambda_u245", ImagesContract.LOCAL, "uid", "access", Scopes.PROFILE, "displayName", "role", "email", "auth", "remember", "$i$a$-runCatching-MasrofViewModel$authenticate$2", "context", HintConstants.AUTOFILL_HINT_USERNAME, "password", "$this$authenticate_u24lambda_u245", ImagesContract.LOCAL, "uid", "access", Scopes.PROFILE, "displayName", "role", "email", "auth", "remember", "$i$a$-runCatching-MasrofViewModel$authenticate$2", "context", HintConstants.AUTOFILL_HINT_USERNAME, "password", "it", "remember", "$i$a$-getOrElse-MasrofViewModel$authenticate$3"}, m943s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "Z$0", "I$0"})
    static final class C40601 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C40601(Continuation<? super C40601> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MasrofViewModel.this.authenticate(null, null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$authenticateFirstLogin$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel", m938f = "MasrofViewModel.kt", m939i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4}, m940l = {ComposerKt.reuseKey, 208, 211, 214, 215}, m941m = "authenticateFirstLogin", m942n = {"context", "email", "password", "$this$authenticateFirstLogin_u24lambda_u247", "$i$a$-runCatching-MasrofViewModel$authenticateFirstLogin$2", "context", "email", "password", "$this$authenticateFirstLogin_u24lambda_u247", "uid", "$i$a$-runCatching-MasrofViewModel$authenticateFirstLogin$2", "context", "email", "password", "$this$authenticateFirstLogin_u24lambda_u247", "uid", "access", "$i$a$-runCatching-MasrofViewModel$authenticateFirstLogin$2", "context", "email", "password", "$this$authenticateFirstLogin_u24lambda_u247", "uid", "access", Scopes.PROFILE, "auth", "$i$a$-runCatching-MasrofViewModel$authenticateFirstLogin$2", "context", "email", "password", "$this$authenticateFirstLogin_u24lambda_u247", "uid", "access", Scopes.PROFILE, "auth", "$i$a$-runCatching-MasrofViewModel$authenticateFirstLogin$2"}, m943s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0"})
    static final class C40611 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        C40611(Continuation<? super C40611> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MasrofViewModel.this.authenticateFirstLogin(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$cacheLocalUser$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel", m938f = "MasrofViewModel.kt", m939i = {0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, m940l = {198, 201, 201}, m941m = "cacheLocalUser", m942n = {"auth", "password", "auth", "password", "existing", "role", ImagesContract.LOCAL, "auth", "password", "existing", "role", ImagesContract.LOCAL}, m943s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class C40621 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C40621(Continuation<? super C40621> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MasrofViewModel.this.cacheLocalUser(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$completeFirstLoginUsername$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel", m938f = "MasrofViewModel.kt", m939i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5}, m940l = {227, 228, 231, 231, 233, 234}, m941m = "completeFirstLoginUsername", m942n = {"context", HintConstants.AUTOFILL_HINT_USERNAME, "$this$completeFirstLoginUsername_u24lambda_u2410", "email", "user", "remember", "$i$a$-runCatching-MasrofViewModel$completeFirstLoginUsername$2", "context", HintConstants.AUTOFILL_HINT_USERNAME, "$this$completeFirstLoginUsername_u24lambda_u2410", "email", "user", "remember", "$i$a$-runCatching-MasrofViewModel$completeFirstLoginUsername$2", "context", HintConstants.AUTOFILL_HINT_USERNAME, "$this$completeFirstLoginUsername_u24lambda_u2410", "email", "user", Scopes.PROFILE, "auth", "remember", "$i$a$-runCatching-MasrofViewModel$completeFirstLoginUsername$2", "context", HintConstants.AUTOFILL_HINT_USERNAME, "$this$completeFirstLoginUsername_u24lambda_u2410", "email", "user", Scopes.PROFILE, "auth", "existing", "remember", "$i$a$-runCatching-MasrofViewModel$completeFirstLoginUsername$2", "$i$a$-let-MasrofViewModel$completeFirstLoginUsername$2$1", "context", HintConstants.AUTOFILL_HINT_USERNAME, "$this$completeFirstLoginUsername_u24lambda_u2410", "email", "user", Scopes.PROFILE, "auth", "remember", "$i$a$-runCatching-MasrofViewModel$completeFirstLoginUsername$2", "context", HintConstants.AUTOFILL_HINT_USERNAME, "$this$completeFirstLoginUsername_u24lambda_u2410", "email", "user", Scopes.PROFILE, "auth", "remember", "$i$a$-runCatching-MasrofViewModel$completeFirstLoginUsername$2"}, m943s = {"L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0"})
    static final class C40631 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C40631(Continuation<? super C40631> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MasrofViewModel.this.completeFirstLoginUsername(null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$registerUser$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel", m938f = "MasrofViewModel.kt", m939i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, m940l = {242, 243}, m941m = "registerUser", m942n = {HintConstants.AUTOFILL_HINT_USERNAME, "password", "fullName", "role", "$this$registerUser_u24lambda_u2412", "$i$a$-runCatching-MasrofViewModel$registerUser$2", HintConstants.AUTOFILL_HINT_USERNAME, "password", "fullName", "role", "$this$registerUser_u24lambda_u2412", "$i$a$-runCatching-MasrofViewModel$registerUser$2"}, m943s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0"})
    static final class C40761 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C40761(Continuation<? super C40761> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MasrofViewModel.this.registerUser(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$restoreLocalUser$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel", m938f = "MasrofViewModel.kt", m939i = {0, 0}, m940l = {152}, m941m = "restoreLocalUser", m942n = {"context", HintConstants.AUTOFILL_HINT_USERNAME}, m943s = {"L$0", "L$1"})
    static final class C40781 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C40781(Continuation<? super C40781> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MasrofViewModel.this.restoreLocalUser(null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$restoreRememberedUser$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel", m938f = "MasrofViewModel.kt", m939i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, m940l = {138, 141}, m941m = "restoreRememberedUser", m942n = {"context", "$this$restoreRememberedUser_u24lambda_u244", "uid", "$i$a$-runCatching-MasrofViewModel$restoreRememberedUser$2", "context", "$this$restoreRememberedUser_u24lambda_u244", "access", "uid", "$i$a$-runCatching-MasrofViewModel$restoreRememberedUser$2"}, m943s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
    static final class C40791 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C40791(Continuation<? super C40791> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MasrofViewModel.this.restoreRememberedUser(null, this);
        }
    }

    public /* synthetic */ MasrofViewModel(MasrofRepository masrofRepository, MasrofDatabase masrofDatabase, FirebaseCloudSync firebaseCloudSync, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(masrofRepository, masrofDatabase, (i & 4) != 0 ? FirebaseCloudSync.INSTANCE : firebaseCloudSync);
    }

    public MasrofViewModel(MasrofRepository repository, MasrofDatabase database, FirebaseCloudSync cloud) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(cloud, "cloud");
        this.repository = repository;
        this.database = database;
        this.cloud = cloud;
        MasrofViewModel masrofViewModel = this;
        this.allDocuments = FlowKt.stateIn(repository.getAllDocuments(), ViewModelKt.getViewModelScope(masrofViewModel), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, 5000L, 0L, 2, null), CollectionsKt.emptyList());
        this.archivedDocuments = FlowKt.stateIn(repository.getArchivedDocuments(), ViewModelKt.getViewModelScope(masrofViewModel), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, 5000L, 0L, 2, null), CollectionsKt.emptyList());
        this.lastDocumentNumber = FlowKt.stateIn(repository.getLastDocumentNumber(), ViewModelKt.getViewModelScope(masrofViewModel), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, 5000L, 0L, 2, null), 0);
        this.allUsers = FlowKt.stateIn(repository.getAllUsers(), ViewModelKt.getViewModelScope(masrofViewModel), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, 5000L, 0L, 2, null), CollectionsKt.emptyList());
        this.auditLogs = FlowKt.stateIn(repository.getAuditLogs(), ViewModelKt.getViewModelScope(masrofViewModel), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, 5000L, 0L, 2, null), CollectionsKt.emptyList());
        this.designElements = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.activeDesign = StateFlowKt.MutableStateFlow(null);
        this.undoStack = new ArrayDeque<>();
        this.redoStack = new ArrayDeque<>();
    }

    public final String getLastAuthError() {
        return this.lastAuthError;
    }

    public final void setLastAuthError(String str) {
        this.lastAuthError = str;
    }

    /* JADX INFO: renamed from: isManagerSession, reason: from getter */
    public final boolean getIsManagerSession() {
        return this.isManagerSession;
    }

    public final void setManagerSession(boolean z) {
        this.isManagerSession = z;
    }

    public final StateFlow<List<Document>> getAllDocuments() {
        return this.allDocuments;
    }

    public final StateFlow<List<Document>> getArchivedDocuments() {
        return this.archivedDocuments;
    }

    public final StateFlow<Integer> getLastDocumentNumber() {
        return this.lastDocumentNumber;
    }

    public final StateFlow<List<UserEntity>> getAllUsers() {
        return this.allUsers;
    }

    public final StateFlow<List<AuditLogEntity>> getAuditLogs() {
        return this.auditLogs;
    }

    public final MutableStateFlow<List<DesignElementEntity>> getDesignElements() {
        return this.designElements;
    }

    public final MutableStateFlow<DocumentDesignEntity> getActiveDesign() {
        return this.activeDesign;
    }

    public final DesignElementEntity getClipboard() {
        return this.clipboard;
    }

    public final void setClipboard(DesignElementEntity designElementEntity) {
        this.clipboard = designElementEntity;
    }

    private final List<DesignElementEntity> snapshot() {
        List<DesignElementEntity> value = this.designElements.getValue();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(value, 10));
        Iterator<T> it = value.iterator();
        while (it.hasNext()) {
            arrayList.add(DesignElementEntity.copy$default((DesignElementEntity) it.next(), 0L, 0L, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554431, null));
        }
        return arrayList;
    }

    private final void rememberChange() {
        this.undoStack.addLast(snapshot());
        this.redoStack.clear();
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$loadDesign$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$loadDesign$1", m938f = "MasrofViewModel.kt", m939i = {0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7}, m940l = {63, 64, RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH, 69, 87, 88, 99, 99}, m941m = "invokeSuspend", m942n = {"$this$launch", "$this$launch", "$this$invokeSuspend_u24lambda_u240", "$i$a$-run-MasrofViewModel$loadDesign$1$design$1", "$this$launch", "$this$invokeSuspend_u24lambda_u240", "$i$a$-run-MasrofViewModel$loadDesign$1$design$1", "id", "$this$launch", "design", "$this$launch", "design", "savedElements", "navy", "template", "$this$forEach$iv", "element$iv", "it", "$i$f$forEach", "$i$a$-forEach-MasrofViewModel$loadDesign$1$1", "$this$launch", "design", "savedElements", "navy", "template", "$this$launch", "design", "savedElements", "migrated", "$this$launch", "design", "savedElements", "migrated"}, m943s = {"L$0", "L$0", "L$3", "I$0", "L$0", "L$2", "I$0", "J$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$8", "L$9", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    static final class C40721 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DocumentType $type;
        int I$0;
        int I$1;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$loadDesign$1$WhenMappings */
        /* JADX INFO: compiled from: MasrofViewModel.kt */
        @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[DocumentType.values().length];
                try {
                    iArr[DocumentType.ORDER.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[DocumentType.RECEIPT.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40721(DocumentType documentType, Continuation<? super C40721> continuation) {
            super(2, continuation);
            this.$type = documentType;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C40721 c40721 = MasrofViewModel.this.new C40721(this.$type, continuation);
            c40721.L$0 = obj;
            return c40721;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40721) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x0093 A[PHI: r3 r4 r16 r17 r18
          0x0093: PHI (r3v19 com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity) = 
          (r3v11 com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity)
          (r3v31 com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity)
         binds: [B:34:0x01d0, B:10:0x0084] A[DONT_GENERATE, DONT_INLINE]
          0x0093: PHI (r4v7 java.lang.Object) = (r4v6 java.lang.Object), (r4v9 java.lang.Object) binds: [B:34:0x01d0, B:10:0x0084] A[DONT_GENERATE, DONT_INLINE]
          0x0093: PHI (r16v8 int) = (r16v5 int), (r16v9 int) binds: [B:34:0x01d0, B:10:0x0084] A[DONT_GENERATE, DONT_INLINE]
          0x0093: PHI (r17v6 int) = (r17v3 int), (r17v7 int) binds: [B:34:0x01d0, B:10:0x0084] A[DONT_GENERATE, DONT_INLINE]
          0x0093: PHI (r18v5 int) = (r18v2 int), (r18v6 int) binds: [B:34:0x01d0, B:10:0x0084] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:20:0x00ee  */
        /* JADX WARN: Code duplicated, block: B:23:0x013d  */
        /* JADX WARN: Code duplicated, block: B:27:0x016b  */
        /* JADX WARN: Code duplicated, block: B:30:0x0171  */
        /* JADX WARN: Code duplicated, block: B:31:0x0199  */
        /* JADX WARN: Code duplicated, block: B:32:0x019b  */
        /* JADX WARN: Code duplicated, block: B:38:0x01df  */
        /* JADX WARN: Code duplicated, block: B:40:0x03b3 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:41:0x03b5  */
        /* JADX WARN: Code duplicated, block: B:42:0x03b8  */
        /* JADX WARN: Code duplicated, block: B:43:0x03bb  */
        /* JADX WARN: Code duplicated, block: B:46:0x03c5  */
        /* JADX WARN: Code duplicated, block: B:47:0x03c8  */
        /* JADX WARN: Code duplicated, block: B:51:0x0459  */
        /* JADX WARN: Code duplicated, block: B:55:0x04a8  */
        /* JADX WARN: Code duplicated, block: B:59:0x04ef  */
        /* JADX WARN: Code duplicated, block: B:62:0x0509  */
        /* JADX WARN: Code duplicated, block: B:64:0x051d  */
        /* JADX WARN: Code duplicated, block: B:68:0x0538 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:70:0x053c  */
        /* JADX WARN: Code duplicated, block: B:71:0x0579  */
        /* JADX WARN: Code duplicated, block: B:72:0x05b6 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:76:0x05c7  */
        /* JADX WARN: Code duplicated, block: B:79:0x05f1 A[PHI: r3 r4 r5 r16
          0x05f1: PHI (r3v37 java.util.List<com.mohammedalhzmi.masrofmanager.data.DesignElementEntity>) = (r3v25 java.util.ArrayList), (r3v39 java.util.List<com.mohammedalhzmi.masrofmanager.data.DesignElementEntity>) binds: [B:77:0x05ee, B:7:0x0030] A[DONT_GENERATE, DONT_INLINE]
          0x05f1: PHI (r4v20 java.util.List<com.mohammedalhzmi.masrofmanager.data.DesignElementEntity>) = 
          (r4v8 java.util.List<com.mohammedalhzmi.masrofmanager.data.DesignElementEntity>)
          (r4v22 java.util.List<com.mohammedalhzmi.masrofmanager.data.DesignElementEntity>)
         binds: [B:77:0x05ee, B:7:0x0030] A[DONT_GENERATE, DONT_INLINE]
          0x05f1: PHI (r5v6 com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity) = 
          (r5v2 com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity)
          (r5v8 com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity)
         binds: [B:77:0x05ee, B:7:0x0030] A[DONT_GENERATE, DONT_INLINE]
          0x05f1: PHI (r16v10 int) = (r16v8 int), (r16v11 int) binds: [B:77:0x05ee, B:7:0x0030] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:90:0x05b7 A[SYNTHETIC] */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x04a0, code lost:
        
            if (r15.addDesignElement(r5, r79) == r2) goto L81;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x04e6, code lost:
        
            if (r1 == r2) goto L81;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x061e, code lost:
        
            if (r1 == r2) goto L81;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x04a0 -> B:54:0x04a4). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object design;
            DocumentDesignEntity documentDesignEntity;
            int i;
            int i2;
            int i3;
            MasrofViewModel masrofViewModel;
            DocumentType documentType;
            Object objSaveDesign;
            CoroutineScope coroutineScope;
            int i4;
            long jLongValue;
            Object design2;
            long j;
            Object objDesignElements;
            DocumentDesignEntity documentDesignEntity2;
            DocumentDesignEntity documentDesignEntity3;
            List<DesignElementEntity> list;
            DocumentType documentType2;
            ArrayList arrayList;
            ArrayList arrayList2;
            int i5;
            int i6;
            String str;
            float f;
            MasrofViewModel masrofViewModel2;
            Iterable iterable;
            Iterator it;
            List list2;
            List<DesignElementEntity> list3;
            DocumentDesignEntity documentDesignEntity4;
            int i7;
            String str2;
            Object objDesignElements2;
            int i8;
            Object objDesignElements3;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.L$0 = coroutineScope2;
                    this.label = 1;
                    design = MasrofViewModel.this.repository.getDesign(this.$type, this);
                    if (design != coroutine_suspended) {
                        documentDesignEntity = (DocumentDesignEntity) design;
                        if (documentDesignEntity == null) {
                            masrofViewModel = MasrofViewModel.this;
                            documentType = this.$type;
                            MasrofRepository masrofRepository = masrofViewModel.repository;
                            DocumentDesignEntity documentDesignEntity5 = new DocumentDesignEntity(0L, documentType.name(), documentType.name(), 0.0f, 0.0f, null, null, 0L, null, 0.0f, 0.0f, 0.0f, 0.0f, 8185, null);
                            i = 8;
                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                            this.L$1 = masrofViewModel;
                            this.L$2 = documentType;
                            this.L$3 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                            this.I$0 = 0;
                            this.label = 2;
                            objSaveDesign = masrofRepository.saveDesign(documentDesignEntity5, this);
                            if (objSaveDesign != coroutine_suspended) {
                                coroutineScope = coroutineScope2;
                                i4 = 0;
                                i2 = 6;
                                i3 = 5;
                                jLongValue = ((Number) objSaveDesign).longValue();
                                MasrofRepository masrofRepository2 = masrofViewModel.repository;
                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                this.L$1 = documentType;
                                this.L$2 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                this.L$3 = null;
                                this.I$0 = i4;
                                this.J$0 = jLongValue;
                                this.label = 3;
                                design2 = masrofRepository2.getDesign(documentType, this);
                                if (design2 != coroutine_suspended) {
                                    j = jLongValue;
                                    documentDesignEntity2 = (DocumentDesignEntity) design2;
                                    if (documentDesignEntity2 == null) {
                                        documentDesignEntity = new DocumentDesignEntity(j, documentType.name(), documentType.name(), 0.0f, 0.0f, null, null, 0L, null, 0.0f, 0.0f, 0.0f, 0.0f, 8184, null);
                                    } else {
                                        documentDesignEntity = documentDesignEntity2;
                                    }
                                    MasrofViewModel.this.activeDesignId = documentDesignEntity.getId();
                                    MasrofViewModel.this.getActiveDesign().setValue(documentDesignEntity);
                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                    this.L$1 = documentDesignEntity;
                                    this.L$2 = null;
                                    this.label = 4;
                                    objDesignElements = MasrofViewModel.this.repository.designElements(documentDesignEntity.getId(), this);
                                    if (objDesignElements != coroutine_suspended) {
                                        documentDesignEntity3 = documentDesignEntity;
                                        list = (List) objDesignElements;
                                        if (list.isEmpty()) {
                                            DesignElementEntity[] designElementEntityArr = new DesignElementEntity[11];
                                            designElementEntityArr[0] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 24.0f, 24.0f, 547.0f, 794.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, "#FFFFFF", "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32635657, null);
                                            designElementEntityArr[1] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية\nوزارة الإدارة والتنمية المحلية والريفية\nصندوق النظافة والتحسين م/إب\nفرع مديرية الحزم", 300.0f, 42.0f, 240.0f, 92.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "END", 1.35f, 0.0f, 20939521, null);
                                            designElementEntityArr[2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية", 55.0f, 46.0f, 170.0f, 28.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33522433, null);
                                            designElementEntityArr[3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "NO: {رقم المستند}\nالتاريخ الهجري: {التاريخ الهجري}\nالموافق: {التاريخ الميلادي}", 55.0f, 88.0f, 220.0f, 72.0f, 0.0f, 0.0f, 1, "AMIRI", 12.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.45f, 0.0f, 25150209, null);
                                            designElementEntityArr[4] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "LINE", null, 42.0f, 172.0f, 511.0f, 4.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, null, "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32766729, null);
                                            designElementEntityArr[i3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", ModelsKt.displayName(this.$type), 160.0f, 190.0f, 275.0f, 40.0f, 0.0f, 0.0f, 3, "AMIRI", 22.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29328129, null);
                                            designElementEntityArr[i2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "اسم المستفيد / مقدم الطلب: {اسم المستفيد}\nالغرض والبيان: {الغرض}\nالمبلغ: {المبلغ}\nالمبلغ كتابةً: {المبلغ كتابة}", 70.0f, 260.0f, 455.0f, 150.0f, 0.0f, 0.0f, 3, "AMIRI", 15.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.7f, 0.0f, 20955905, null);
                                            designElementEntityArr[7] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 62.0f, 242.0f, 471.0f, 190.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, "#FAFCFD", "#B48A3A", 1.5f, false, false, null, 0.0f, 0.0f, 32635657, null);
                                            designElementEntityArr[i] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "البند المالي: {البند المالي}\nمركز التكلفة: {مركز التكلفة}\nمصدر التمويل: {مصدر التمويل}", 70.0f, 455.0f, 455.0f, 92.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.55f, 0.0f, 20955905, null);
                                            long id = documentDesignEntity3.getId();
                                            i6 = WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()];
                                            if (i6 == 1) {
                                                str = "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................";
                                            } else if (i6 != 2) {
                                                str = "مقدم الطلب\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                                            } else {
                                                str = "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                                            }
                                            String str3 = str;
                                            if (this.$type == DocumentType.ORDER) {
                                                f = 330.0f;
                                            } else {
                                                f = 55.0f;
                                            }
                                            designElementEntityArr[9] = new DesignElementEntity(0L, id, "TEXT", str3, f, 690.0f, 225.0f, 105.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.5f, 0.0f, 25150209, null);
                                            designElementEntityArr[10] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "طبع بواسطة نظام مالية فرع صندوق النظافة والتحسين مديرية الحزم", 55.0f, 805.0f, 485.0f, 22.0f, 0.0f, 0.0f, 3, "AMIRI", 9.0f, "#555555", false, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29344513, null);
                                            List listListOf = CollectionsKt.listOf((Object[]) designElementEntityArr);
                                            List list4 = listListOf;
                                            masrofViewModel2 = MasrofViewModel.this;
                                            iterable = list4;
                                            it = list4.iterator();
                                            list2 = listListOf;
                                            list3 = list;
                                            documentDesignEntity4 = documentDesignEntity3;
                                            i7 = 0;
                                            str2 = "#123B5D";
                                            if (it.hasNext()) {
                                                Object next = it.next();
                                                DesignElementEntity designElementEntity = (DesignElementEntity) next;
                                                MasrofRepository masrofRepository3 = masrofViewModel2.repository;
                                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                                this.L$1 = documentDesignEntity4;
                                                this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                                                this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                                                this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                                                this.L$5 = SpillingKt.nullOutSpilledVariable(iterable);
                                                this.L$6 = masrofViewModel2;
                                                this.L$7 = it;
                                                this.L$8 = SpillingKt.nullOutSpilledVariable(next);
                                                this.L$9 = SpillingKt.nullOutSpilledVariable(designElementEntity);
                                                this.I$0 = i7;
                                                this.I$1 = 0;
                                                i8 = i3;
                                                this.label = i8;
                                            } else {
                                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                                this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity4);
                                                this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                                                this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                                                this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                                                this.L$5 = null;
                                                this.L$6 = null;
                                                this.L$7 = null;
                                                this.L$8 = null;
                                                this.L$9 = null;
                                                this.label = i2;
                                                objDesignElements2 = MasrofViewModel.this.repository.designElements(documentDesignEntity4.getId(), this);
                                            }
                                        } else {
                                            List<DesignElementEntity> list5 = list;
                                            documentType2 = this.$type;
                                            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list5, 10));
                                            for (DesignElementEntity designElementEntityCopy$default : list5) {
                                                if (!Intrinsics.areEqual(designElementEntityCopy$default.getType(), "TEXT") && StringsKt.contains$default((CharSequence) designElementEntityCopy$default.getContent(), (CharSequence) "مقدم الطلب", false, 2, (Object) null)) {
                                                    i5 = WhenMappings.$EnumSwitchMapping$0[documentType2.ordinal()];
                                                    if (i5 == 1) {
                                                        designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................", 330.0f, 690.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554375, null);
                                                    } else if (i5 == 2) {
                                                        designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................", 55.0f, 650.0f, 0.0f, 125.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554247, null);
                                                    }
                                                }
                                                arrayList.add(designElementEntityCopy$default);
                                            }
                                            arrayList2 = arrayList;
                                            if (!Intrinsics.areEqual(arrayList2, list)) {
                                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                                this.L$1 = documentDesignEntity3;
                                                this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                                                this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                                                this.label = 7;
                                                if (MasrofViewModel.this.repository.replaceDesignElements(documentDesignEntity3.getId(), arrayList2, this) != coroutine_suspended) {
                                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                                    this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity3);
                                                    this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                                                    this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                                                    this.label = i;
                                                    objDesignElements3 = MasrofViewModel.this.repository.designElements(documentDesignEntity3.getId(), this);
                                                }
                                                break;
                                            }
                                            MasrofViewModel.this.getDesignElements().setValue(list);
                                            return Unit.INSTANCE;
                                        }
                                    }
                                }
                            }
                        } else {
                            i = 8;
                            i2 = 6;
                            i3 = 5;
                            MasrofViewModel.this.activeDesignId = documentDesignEntity.getId();
                            MasrofViewModel.this.getActiveDesign().setValue(documentDesignEntity);
                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                            this.L$1 = documentDesignEntity;
                            this.L$2 = null;
                            this.label = 4;
                            objDesignElements = MasrofViewModel.this.repository.designElements(documentDesignEntity.getId(), this);
                            if (objDesignElements != coroutine_suspended) {
                                documentDesignEntity3 = documentDesignEntity;
                                list = (List) objDesignElements;
                                if (list.isEmpty()) {
                                    DesignElementEntity[] designElementEntityArr2 = new DesignElementEntity[11];
                                    designElementEntityArr2[0] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 24.0f, 24.0f, 547.0f, 794.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, "#FFFFFF", "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32635657, null);
                                    designElementEntityArr2[1] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية\nوزارة الإدارة والتنمية المحلية والريفية\nصندوق النظافة والتحسين م/إب\nفرع مديرية الحزم", 300.0f, 42.0f, 240.0f, 92.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "END", 1.35f, 0.0f, 20939521, null);
                                    designElementEntityArr2[2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية", 55.0f, 46.0f, 170.0f, 28.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33522433, null);
                                    designElementEntityArr2[3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "NO: {رقم المستند}\nالتاريخ الهجري: {التاريخ الهجري}\nالموافق: {التاريخ الميلادي}", 55.0f, 88.0f, 220.0f, 72.0f, 0.0f, 0.0f, 1, "AMIRI", 12.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.45f, 0.0f, 25150209, null);
                                    designElementEntityArr2[4] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "LINE", null, 42.0f, 172.0f, 511.0f, 4.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, null, "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32766729, null);
                                    designElementEntityArr2[i3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", ModelsKt.displayName(this.$type), 160.0f, 190.0f, 275.0f, 40.0f, 0.0f, 0.0f, 3, "AMIRI", 22.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29328129, null);
                                    designElementEntityArr2[i2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "اسم المستفيد / مقدم الطلب: {اسم المستفيد}\nالغرض والبيان: {الغرض}\nالمبلغ: {المبلغ}\nالمبلغ كتابةً: {المبلغ كتابة}", 70.0f, 260.0f, 455.0f, 150.0f, 0.0f, 0.0f, 3, "AMIRI", 15.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.7f, 0.0f, 20955905, null);
                                    designElementEntityArr2[7] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 62.0f, 242.0f, 471.0f, 190.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, "#FAFCFD", "#B48A3A", 1.5f, false, false, null, 0.0f, 0.0f, 32635657, null);
                                    designElementEntityArr2[i] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "البند المالي: {البند المالي}\nمركز التكلفة: {مركز التكلفة}\nمصدر التمويل: {مصدر التمويل}", 70.0f, 455.0f, 455.0f, 92.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.55f, 0.0f, 20955905, null);
                                    long id2 = documentDesignEntity3.getId();
                                    i6 = WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()];
                                    if (i6 == 1) {
                                        str = "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................";
                                    } else if (i6 != 2) {
                                        str = "مقدم الطلب\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                                    } else {
                                        str = "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                                    }
                                    String str4 = str;
                                    if (this.$type == DocumentType.ORDER) {
                                        f = 330.0f;
                                    } else {
                                        f = 55.0f;
                                    }
                                    designElementEntityArr2[9] = new DesignElementEntity(0L, id2, "TEXT", str4, f, 690.0f, 225.0f, 105.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.5f, 0.0f, 25150209, null);
                                    designElementEntityArr2[10] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "طبع بواسطة نظام مالية فرع صندوق النظافة والتحسين مديرية الحزم", 55.0f, 805.0f, 485.0f, 22.0f, 0.0f, 0.0f, 3, "AMIRI", 9.0f, "#555555", false, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29344513, null);
                                    List listListOf2 = CollectionsKt.listOf((Object[]) designElementEntityArr2);
                                    List list6 = listListOf2;
                                    masrofViewModel2 = MasrofViewModel.this;
                                    iterable = list6;
                                    it = list6.iterator();
                                    list2 = listListOf2;
                                    list3 = list;
                                    documentDesignEntity4 = documentDesignEntity3;
                                    i7 = 0;
                                    str2 = "#123B5D";
                                    if (it.hasNext()) {
                                        Object next2 = it.next();
                                        DesignElementEntity designElementEntity2 = (DesignElementEntity) next2;
                                        MasrofRepository masrofRepository4 = masrofViewModel2.repository;
                                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                        this.L$1 = documentDesignEntity4;
                                        this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                                        this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                                        this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                                        this.L$5 = SpillingKt.nullOutSpilledVariable(iterable);
                                        this.L$6 = masrofViewModel2;
                                        this.L$7 = it;
                                        this.L$8 = SpillingKt.nullOutSpilledVariable(next2);
                                        this.L$9 = SpillingKt.nullOutSpilledVariable(designElementEntity2);
                                        this.I$0 = i7;
                                        this.I$1 = 0;
                                        i8 = i3;
                                        this.label = i8;
                                    } else {
                                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                        this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity4);
                                        this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                                        this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                                        this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                                        this.L$5 = null;
                                        this.L$6 = null;
                                        this.L$7 = null;
                                        this.L$8 = null;
                                        this.L$9 = null;
                                        this.label = i2;
                                        objDesignElements2 = MasrofViewModel.this.repository.designElements(documentDesignEntity4.getId(), this);
                                    }
                                } else {
                                    List<DesignElementEntity> list7 = list;
                                    documentType2 = this.$type;
                                    arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list7, 10));
                                    while (r3.hasNext()) {
                                        if (!Intrinsics.areEqual(designElementEntityCopy$default.getType(), "TEXT")) {
                                            i5 = WhenMappings.$EnumSwitchMapping$0[documentType2.ordinal()];
                                            if (i5 == 1) {
                                                designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................", 330.0f, 690.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554375, null);
                                            } else if (i5 == 2) {
                                                designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................", 55.0f, 650.0f, 0.0f, 125.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554247, null);
                                            }
                                        }
                                        arrayList.add(designElementEntityCopy$default);
                                    }
                                    arrayList2 = arrayList;
                                    if (!Intrinsics.areEqual(arrayList2, list)) {
                                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                        this.L$1 = documentDesignEntity3;
                                        this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                                        this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                                        this.label = 7;
                                        if (MasrofViewModel.this.repository.replaceDesignElements(documentDesignEntity3.getId(), arrayList2, this) != coroutine_suspended) {
                                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                            this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity3);
                                            this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                                            this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                                            this.label = i;
                                            objDesignElements3 = MasrofViewModel.this.repository.designElements(documentDesignEntity3.getId(), this);
                                        }
                                        break;
                                    }
                                    MasrofViewModel.this.getDesignElements().setValue(list);
                                    return Unit.INSTANCE;
                                }
                            }
                        }
                        break;
                    }
                    return coroutine_suspended;
                case 1:
                    ResultKt.throwOnFailure(obj);
                    design = obj;
                    documentDesignEntity = (DocumentDesignEntity) design;
                    if (documentDesignEntity == null) {
                        masrofViewModel = MasrofViewModel.this;
                        documentType = this.$type;
                        MasrofRepository masrofRepository5 = masrofViewModel.repository;
                        DocumentDesignEntity documentDesignEntity6 = new DocumentDesignEntity(0L, documentType.name(), documentType.name(), 0.0f, 0.0f, null, null, 0L, null, 0.0f, 0.0f, 0.0f, 0.0f, 8185, null);
                        i = 8;
                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                        this.L$1 = masrofViewModel;
                        this.L$2 = documentType;
                        this.L$3 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                        this.I$0 = 0;
                        this.label = 2;
                        objSaveDesign = masrofRepository5.saveDesign(documentDesignEntity6, this);
                        if (objSaveDesign != coroutine_suspended) {
                            coroutineScope = coroutineScope2;
                            i4 = 0;
                            i2 = 6;
                            i3 = 5;
                            jLongValue = ((Number) objSaveDesign).longValue();
                            MasrofRepository masrofRepository6 = masrofViewModel.repository;
                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                            this.L$1 = documentType;
                            this.L$2 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                            this.L$3 = null;
                            this.I$0 = i4;
                            this.J$0 = jLongValue;
                            this.label = 3;
                            design2 = masrofRepository6.getDesign(documentType, this);
                            if (design2 != coroutine_suspended) {
                                j = jLongValue;
                                documentDesignEntity2 = (DocumentDesignEntity) design2;
                                if (documentDesignEntity2 == null) {
                                    documentDesignEntity = new DocumentDesignEntity(j, documentType.name(), documentType.name(), 0.0f, 0.0f, null, null, 0L, null, 0.0f, 0.0f, 0.0f, 0.0f, 8184, null);
                                } else {
                                    documentDesignEntity = documentDesignEntity2;
                                }
                                MasrofViewModel.this.activeDesignId = documentDesignEntity.getId();
                                MasrofViewModel.this.getActiveDesign().setValue(documentDesignEntity);
                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                this.L$1 = documentDesignEntity;
                                this.L$2 = null;
                                this.label = 4;
                                objDesignElements = MasrofViewModel.this.repository.designElements(documentDesignEntity.getId(), this);
                                if (objDesignElements != coroutine_suspended) {
                                    documentDesignEntity3 = documentDesignEntity;
                                    list = (List) objDesignElements;
                                    if (list.isEmpty()) {
                                        DesignElementEntity[] designElementEntityArr3 = new DesignElementEntity[11];
                                        designElementEntityArr3[0] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 24.0f, 24.0f, 547.0f, 794.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, "#FFFFFF", "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32635657, null);
                                        designElementEntityArr3[1] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية\nوزارة الإدارة والتنمية المحلية والريفية\nصندوق النظافة والتحسين م/إب\nفرع مديرية الحزم", 300.0f, 42.0f, 240.0f, 92.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "END", 1.35f, 0.0f, 20939521, null);
                                        designElementEntityArr3[2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية", 55.0f, 46.0f, 170.0f, 28.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33522433, null);
                                        designElementEntityArr3[3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "NO: {رقم المستند}\nالتاريخ الهجري: {التاريخ الهجري}\nالموافق: {التاريخ الميلادي}", 55.0f, 88.0f, 220.0f, 72.0f, 0.0f, 0.0f, 1, "AMIRI", 12.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.45f, 0.0f, 25150209, null);
                                        designElementEntityArr3[4] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "LINE", null, 42.0f, 172.0f, 511.0f, 4.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, null, "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32766729, null);
                                        designElementEntityArr3[i3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", ModelsKt.displayName(this.$type), 160.0f, 190.0f, 275.0f, 40.0f, 0.0f, 0.0f, 3, "AMIRI", 22.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29328129, null);
                                        designElementEntityArr3[i2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "اسم المستفيد / مقدم الطلب: {اسم المستفيد}\nالغرض والبيان: {الغرض}\nالمبلغ: {المبلغ}\nالمبلغ كتابةً: {المبلغ كتابة}", 70.0f, 260.0f, 455.0f, 150.0f, 0.0f, 0.0f, 3, "AMIRI", 15.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.7f, 0.0f, 20955905, null);
                                        designElementEntityArr3[7] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 62.0f, 242.0f, 471.0f, 190.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, "#FAFCFD", "#B48A3A", 1.5f, false, false, null, 0.0f, 0.0f, 32635657, null);
                                        designElementEntityArr3[i] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "البند المالي: {البند المالي}\nمركز التكلفة: {مركز التكلفة}\nمصدر التمويل: {مصدر التمويل}", 70.0f, 455.0f, 455.0f, 92.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.55f, 0.0f, 20955905, null);
                                        long id3 = documentDesignEntity3.getId();
                                        i6 = WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()];
                                        if (i6 == 1) {
                                            str = "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................";
                                        } else if (i6 != 2) {
                                            str = "مقدم الطلب\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                                        } else {
                                            str = "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                                        }
                                        String str5 = str;
                                        if (this.$type == DocumentType.ORDER) {
                                            f = 330.0f;
                                        } else {
                                            f = 55.0f;
                                        }
                                        designElementEntityArr3[9] = new DesignElementEntity(0L, id3, "TEXT", str5, f, 690.0f, 225.0f, 105.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.5f, 0.0f, 25150209, null);
                                        designElementEntityArr3[10] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "طبع بواسطة نظام مالية فرع صندوق النظافة والتحسين مديرية الحزم", 55.0f, 805.0f, 485.0f, 22.0f, 0.0f, 0.0f, 3, "AMIRI", 9.0f, "#555555", false, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29344513, null);
                                        List listListOf3 = CollectionsKt.listOf((Object[]) designElementEntityArr3);
                                        List list8 = listListOf3;
                                        masrofViewModel2 = MasrofViewModel.this;
                                        iterable = list8;
                                        it = list8.iterator();
                                        list2 = listListOf3;
                                        list3 = list;
                                        documentDesignEntity4 = documentDesignEntity3;
                                        i7 = 0;
                                        str2 = "#123B5D";
                                        if (it.hasNext()) {
                                            Object next3 = it.next();
                                            DesignElementEntity designElementEntity3 = (DesignElementEntity) next3;
                                            MasrofRepository masrofRepository7 = masrofViewModel2.repository;
                                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                            this.L$1 = documentDesignEntity4;
                                            this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                                            this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                                            this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                                            this.L$5 = SpillingKt.nullOutSpilledVariable(iterable);
                                            this.L$6 = masrofViewModel2;
                                            this.L$7 = it;
                                            this.L$8 = SpillingKt.nullOutSpilledVariable(next3);
                                            this.L$9 = SpillingKt.nullOutSpilledVariable(designElementEntity3);
                                            this.I$0 = i7;
                                            this.I$1 = 0;
                                            i8 = i3;
                                            this.label = i8;
                                        } else {
                                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                            this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity4);
                                            this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                                            this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                                            this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                                            this.L$5 = null;
                                            this.L$6 = null;
                                            this.L$7 = null;
                                            this.L$8 = null;
                                            this.L$9 = null;
                                            this.label = i2;
                                            objDesignElements2 = MasrofViewModel.this.repository.designElements(documentDesignEntity4.getId(), this);
                                        }
                                    } else {
                                        List<DesignElementEntity> list9 = list;
                                        documentType2 = this.$type;
                                        arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list9, 10));
                                        while (r3.hasNext()) {
                                            if (!Intrinsics.areEqual(designElementEntityCopy$default.getType(), "TEXT")) {
                                                i5 = WhenMappings.$EnumSwitchMapping$0[documentType2.ordinal()];
                                                if (i5 == 1) {
                                                    designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................", 330.0f, 690.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554375, null);
                                                } else if (i5 == 2) {
                                                    designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................", 55.0f, 650.0f, 0.0f, 125.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554247, null);
                                                }
                                            }
                                            arrayList.add(designElementEntityCopy$default);
                                        }
                                        arrayList2 = arrayList;
                                        if (!Intrinsics.areEqual(arrayList2, list)) {
                                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                            this.L$1 = documentDesignEntity3;
                                            this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                                            this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                                            this.label = 7;
                                            if (MasrofViewModel.this.repository.replaceDesignElements(documentDesignEntity3.getId(), arrayList2, this) != coroutine_suspended) {
                                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                                this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity3);
                                                this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                                                this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                                                this.label = i;
                                                objDesignElements3 = MasrofViewModel.this.repository.designElements(documentDesignEntity3.getId(), this);
                                            }
                                            break;
                                        }
                                        MasrofViewModel.this.getDesignElements().setValue(list);
                                        return Unit.INSTANCE;
                                    }
                                }
                            }
                        }
                        break;
                    } else {
                        i = 8;
                        i2 = 6;
                        i3 = 5;
                        MasrofViewModel.this.activeDesignId = documentDesignEntity.getId();
                        MasrofViewModel.this.getActiveDesign().setValue(documentDesignEntity);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                        this.L$1 = documentDesignEntity;
                        this.L$2 = null;
                        this.label = 4;
                        objDesignElements = MasrofViewModel.this.repository.designElements(documentDesignEntity.getId(), this);
                        if (objDesignElements != coroutine_suspended) {
                            documentDesignEntity3 = documentDesignEntity;
                            list = (List) objDesignElements;
                            if (list.isEmpty()) {
                                DesignElementEntity[] designElementEntityArr4 = new DesignElementEntity[11];
                                designElementEntityArr4[0] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 24.0f, 24.0f, 547.0f, 794.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, "#FFFFFF", "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32635657, null);
                                designElementEntityArr4[1] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية\nوزارة الإدارة والتنمية المحلية والريفية\nصندوق النظافة والتحسين م/إب\nفرع مديرية الحزم", 300.0f, 42.0f, 240.0f, 92.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "END", 1.35f, 0.0f, 20939521, null);
                                designElementEntityArr4[2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية", 55.0f, 46.0f, 170.0f, 28.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33522433, null);
                                designElementEntityArr4[3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "NO: {رقم المستند}\nالتاريخ الهجري: {التاريخ الهجري}\nالموافق: {التاريخ الميلادي}", 55.0f, 88.0f, 220.0f, 72.0f, 0.0f, 0.0f, 1, "AMIRI", 12.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.45f, 0.0f, 25150209, null);
                                designElementEntityArr4[4] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "LINE", null, 42.0f, 172.0f, 511.0f, 4.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, null, "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32766729, null);
                                designElementEntityArr4[i3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", ModelsKt.displayName(this.$type), 160.0f, 190.0f, 275.0f, 40.0f, 0.0f, 0.0f, 3, "AMIRI", 22.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29328129, null);
                                designElementEntityArr4[i2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "اسم المستفيد / مقدم الطلب: {اسم المستفيد}\nالغرض والبيان: {الغرض}\nالمبلغ: {المبلغ}\nالمبلغ كتابةً: {المبلغ كتابة}", 70.0f, 260.0f, 455.0f, 150.0f, 0.0f, 0.0f, 3, "AMIRI", 15.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.7f, 0.0f, 20955905, null);
                                designElementEntityArr4[7] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 62.0f, 242.0f, 471.0f, 190.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, "#FAFCFD", "#B48A3A", 1.5f, false, false, null, 0.0f, 0.0f, 32635657, null);
                                designElementEntityArr4[i] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "البند المالي: {البند المالي}\nمركز التكلفة: {مركز التكلفة}\nمصدر التمويل: {مصدر التمويل}", 70.0f, 455.0f, 455.0f, 92.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.55f, 0.0f, 20955905, null);
                                long id4 = documentDesignEntity3.getId();
                                i6 = WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()];
                                if (i6 == 1) {
                                    str = "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................";
                                } else if (i6 != 2) {
                                    str = "مقدم الطلب\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                                } else {
                                    str = "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                                }
                                String str6 = str;
                                if (this.$type == DocumentType.ORDER) {
                                    f = 330.0f;
                                } else {
                                    f = 55.0f;
                                }
                                designElementEntityArr4[9] = new DesignElementEntity(0L, id4, "TEXT", str6, f, 690.0f, 225.0f, 105.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.5f, 0.0f, 25150209, null);
                                designElementEntityArr4[10] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "طبع بواسطة نظام مالية فرع صندوق النظافة والتحسين مديرية الحزم", 55.0f, 805.0f, 485.0f, 22.0f, 0.0f, 0.0f, 3, "AMIRI", 9.0f, "#555555", false, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29344513, null);
                                List listListOf4 = CollectionsKt.listOf((Object[]) designElementEntityArr4);
                                List list10 = listListOf4;
                                masrofViewModel2 = MasrofViewModel.this;
                                iterable = list10;
                                it = list10.iterator();
                                list2 = listListOf4;
                                list3 = list;
                                documentDesignEntity4 = documentDesignEntity3;
                                i7 = 0;
                                str2 = "#123B5D";
                                if (it.hasNext()) {
                                    Object next4 = it.next();
                                    DesignElementEntity designElementEntity4 = (DesignElementEntity) next4;
                                    MasrofRepository masrofRepository8 = masrofViewModel2.repository;
                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                    this.L$1 = documentDesignEntity4;
                                    this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                                    this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                                    this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                                    this.L$5 = SpillingKt.nullOutSpilledVariable(iterable);
                                    this.L$6 = masrofViewModel2;
                                    this.L$7 = it;
                                    this.L$8 = SpillingKt.nullOutSpilledVariable(next4);
                                    this.L$9 = SpillingKt.nullOutSpilledVariable(designElementEntity4);
                                    this.I$0 = i7;
                                    this.I$1 = 0;
                                    i8 = i3;
                                    this.label = i8;
                                } else {
                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                    this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity4);
                                    this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                                    this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                                    this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                                    this.L$5 = null;
                                    this.L$6 = null;
                                    this.L$7 = null;
                                    this.L$8 = null;
                                    this.L$9 = null;
                                    this.label = i2;
                                    objDesignElements2 = MasrofViewModel.this.repository.designElements(documentDesignEntity4.getId(), this);
                                }
                            } else {
                                List<DesignElementEntity> list11 = list;
                                documentType2 = this.$type;
                                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list11, 10));
                                while (r3.hasNext()) {
                                    if (!Intrinsics.areEqual(designElementEntityCopy$default.getType(), "TEXT")) {
                                        i5 = WhenMappings.$EnumSwitchMapping$0[documentType2.ordinal()];
                                        if (i5 == 1) {
                                            designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................", 330.0f, 690.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554375, null);
                                        } else if (i5 == 2) {
                                            designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................", 55.0f, 650.0f, 0.0f, 125.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554247, null);
                                        }
                                    }
                                    arrayList.add(designElementEntityCopy$default);
                                }
                                arrayList2 = arrayList;
                                if (!Intrinsics.areEqual(arrayList2, list)) {
                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                    this.L$1 = documentDesignEntity3;
                                    this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                                    this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                                    this.label = 7;
                                    if (MasrofViewModel.this.repository.replaceDesignElements(documentDesignEntity3.getId(), arrayList2, this) != coroutine_suspended) {
                                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                        this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity3);
                                        this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                                        this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                                        this.label = i;
                                        objDesignElements3 = MasrofViewModel.this.repository.designElements(documentDesignEntity3.getId(), this);
                                    }
                                    break;
                                }
                                MasrofViewModel.this.getDesignElements().setValue(list);
                                return Unit.INSTANCE;
                            }
                        }
                        break;
                    }
                    return coroutine_suspended;
                case 2:
                    int i9 = this.I$0;
                    CoroutineScope coroutineScope3 = (CoroutineScope) this.L$3;
                    DocumentType documentType3 = (DocumentType) this.L$2;
                    masrofViewModel = (MasrofViewModel) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    i4 = i9;
                    documentType = documentType3;
                    coroutineScope = coroutineScope3;
                    objSaveDesign = obj;
                    i = 8;
                    i2 = 6;
                    i3 = 5;
                    jLongValue = ((Number) objSaveDesign).longValue();
                    MasrofRepository masrofRepository9 = masrofViewModel.repository;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                    this.L$1 = documentType;
                    this.L$2 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$3 = null;
                    this.I$0 = i4;
                    this.J$0 = jLongValue;
                    this.label = 3;
                    design2 = masrofRepository9.getDesign(documentType, this);
                    if (design2 != coroutine_suspended) {
                        j = jLongValue;
                        documentDesignEntity2 = (DocumentDesignEntity) design2;
                        if (documentDesignEntity2 == null) {
                            documentDesignEntity = new DocumentDesignEntity(j, documentType.name(), documentType.name(), 0.0f, 0.0f, null, null, 0L, null, 0.0f, 0.0f, 0.0f, 0.0f, 8184, null);
                        } else {
                            documentDesignEntity = documentDesignEntity2;
                        }
                        MasrofViewModel.this.activeDesignId = documentDesignEntity.getId();
                        MasrofViewModel.this.getActiveDesign().setValue(documentDesignEntity);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                        this.L$1 = documentDesignEntity;
                        this.L$2 = null;
                        this.label = 4;
                        objDesignElements = MasrofViewModel.this.repository.designElements(documentDesignEntity.getId(), this);
                        if (objDesignElements != coroutine_suspended) {
                            documentDesignEntity3 = documentDesignEntity;
                            list = (List) objDesignElements;
                            if (list.isEmpty()) {
                                DesignElementEntity[] designElementEntityArr5 = new DesignElementEntity[11];
                                designElementEntityArr5[0] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 24.0f, 24.0f, 547.0f, 794.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, "#FFFFFF", "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32635657, null);
                                designElementEntityArr5[1] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية\nوزارة الإدارة والتنمية المحلية والريفية\nصندوق النظافة والتحسين م/إب\nفرع مديرية الحزم", 300.0f, 42.0f, 240.0f, 92.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "END", 1.35f, 0.0f, 20939521, null);
                                designElementEntityArr5[2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية", 55.0f, 46.0f, 170.0f, 28.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33522433, null);
                                designElementEntityArr5[3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "NO: {رقم المستند}\nالتاريخ الهجري: {التاريخ الهجري}\nالموافق: {التاريخ الميلادي}", 55.0f, 88.0f, 220.0f, 72.0f, 0.0f, 0.0f, 1, "AMIRI", 12.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.45f, 0.0f, 25150209, null);
                                designElementEntityArr5[4] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "LINE", null, 42.0f, 172.0f, 511.0f, 4.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, null, "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32766729, null);
                                designElementEntityArr5[i3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", ModelsKt.displayName(this.$type), 160.0f, 190.0f, 275.0f, 40.0f, 0.0f, 0.0f, 3, "AMIRI", 22.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29328129, null);
                                designElementEntityArr5[i2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "اسم المستفيد / مقدم الطلب: {اسم المستفيد}\nالغرض والبيان: {الغرض}\nالمبلغ: {المبلغ}\nالمبلغ كتابةً: {المبلغ كتابة}", 70.0f, 260.0f, 455.0f, 150.0f, 0.0f, 0.0f, 3, "AMIRI", 15.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.7f, 0.0f, 20955905, null);
                                designElementEntityArr5[7] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 62.0f, 242.0f, 471.0f, 190.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, "#FAFCFD", "#B48A3A", 1.5f, false, false, null, 0.0f, 0.0f, 32635657, null);
                                designElementEntityArr5[i] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "البند المالي: {البند المالي}\nمركز التكلفة: {مركز التكلفة}\nمصدر التمويل: {مصدر التمويل}", 70.0f, 455.0f, 455.0f, 92.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.55f, 0.0f, 20955905, null);
                                long id5 = documentDesignEntity3.getId();
                                i6 = WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()];
                                if (i6 == 1) {
                                    str = "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................";
                                } else if (i6 != 2) {
                                    str = "مقدم الطلب\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                                } else {
                                    str = "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                                }
                                String str7 = str;
                                if (this.$type == DocumentType.ORDER) {
                                    f = 330.0f;
                                } else {
                                    f = 55.0f;
                                }
                                designElementEntityArr5[9] = new DesignElementEntity(0L, id5, "TEXT", str7, f, 690.0f, 225.0f, 105.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.5f, 0.0f, 25150209, null);
                                designElementEntityArr5[10] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "طبع بواسطة نظام مالية فرع صندوق النظافة والتحسين مديرية الحزم", 55.0f, 805.0f, 485.0f, 22.0f, 0.0f, 0.0f, 3, "AMIRI", 9.0f, "#555555", false, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29344513, null);
                                List listListOf5 = CollectionsKt.listOf((Object[]) designElementEntityArr5);
                                List list12 = listListOf5;
                                masrofViewModel2 = MasrofViewModel.this;
                                iterable = list12;
                                it = list12.iterator();
                                list2 = listListOf5;
                                list3 = list;
                                documentDesignEntity4 = documentDesignEntity3;
                                i7 = 0;
                                str2 = "#123B5D";
                                if (it.hasNext()) {
                                    Object next5 = it.next();
                                    DesignElementEntity designElementEntity5 = (DesignElementEntity) next5;
                                    MasrofRepository masrofRepository10 = masrofViewModel2.repository;
                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                    this.L$1 = documentDesignEntity4;
                                    this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                                    this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                                    this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                                    this.L$5 = SpillingKt.nullOutSpilledVariable(iterable);
                                    this.L$6 = masrofViewModel2;
                                    this.L$7 = it;
                                    this.L$8 = SpillingKt.nullOutSpilledVariable(next5);
                                    this.L$9 = SpillingKt.nullOutSpilledVariable(designElementEntity5);
                                    this.I$0 = i7;
                                    this.I$1 = 0;
                                    i8 = i3;
                                    this.label = i8;
                                } else {
                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                    this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity4);
                                    this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                                    this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                                    this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                                    this.L$5 = null;
                                    this.L$6 = null;
                                    this.L$7 = null;
                                    this.L$8 = null;
                                    this.L$9 = null;
                                    this.label = i2;
                                    objDesignElements2 = MasrofViewModel.this.repository.designElements(documentDesignEntity4.getId(), this);
                                }
                            } else {
                                List<DesignElementEntity> list13 = list;
                                documentType2 = this.$type;
                                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list13, 10));
                                while (r3.hasNext()) {
                                    if (!Intrinsics.areEqual(designElementEntityCopy$default.getType(), "TEXT")) {
                                        i5 = WhenMappings.$EnumSwitchMapping$0[documentType2.ordinal()];
                                        if (i5 == 1) {
                                            designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................", 330.0f, 690.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554375, null);
                                        } else if (i5 == 2) {
                                            designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................", 55.0f, 650.0f, 0.0f, 125.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554247, null);
                                        }
                                    }
                                    arrayList.add(designElementEntityCopy$default);
                                }
                                arrayList2 = arrayList;
                                if (!Intrinsics.areEqual(arrayList2, list)) {
                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                    this.L$1 = documentDesignEntity3;
                                    this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                                    this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                                    this.label = 7;
                                    if (MasrofViewModel.this.repository.replaceDesignElements(documentDesignEntity3.getId(), arrayList2, this) != coroutine_suspended) {
                                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                        this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity3);
                                        this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                                        this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                                        this.label = i;
                                        objDesignElements3 = MasrofViewModel.this.repository.designElements(documentDesignEntity3.getId(), this);
                                    }
                                    break;
                                }
                                MasrofViewModel.this.getDesignElements().setValue(list);
                                return Unit.INSTANCE;
                            }
                        }
                        break;
                    }
                    return coroutine_suspended;
                case 3:
                    long j2 = this.J$0;
                    documentType = (DocumentType) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    design2 = obj;
                    j = j2;
                    i = 8;
                    i2 = 6;
                    i3 = 5;
                    documentDesignEntity2 = (DocumentDesignEntity) design2;
                    if (documentDesignEntity2 == null) {
                        documentDesignEntity = new DocumentDesignEntity(j, documentType.name(), documentType.name(), 0.0f, 0.0f, null, null, 0L, null, 0.0f, 0.0f, 0.0f, 0.0f, 8184, null);
                    } else {
                        documentDesignEntity = documentDesignEntity2;
                    }
                    MasrofViewModel.this.activeDesignId = documentDesignEntity.getId();
                    MasrofViewModel.this.getActiveDesign().setValue(documentDesignEntity);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                    this.L$1 = documentDesignEntity;
                    this.L$2 = null;
                    this.label = 4;
                    objDesignElements = MasrofViewModel.this.repository.designElements(documentDesignEntity.getId(), this);
                    if (objDesignElements != coroutine_suspended) {
                        documentDesignEntity3 = documentDesignEntity;
                        list = (List) objDesignElements;
                        if (list.isEmpty()) {
                            DesignElementEntity[] designElementEntityArr6 = new DesignElementEntity[11];
                            designElementEntityArr6[0] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 24.0f, 24.0f, 547.0f, 794.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, "#FFFFFF", "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32635657, null);
                            designElementEntityArr6[1] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية\nوزارة الإدارة والتنمية المحلية والريفية\nصندوق النظافة والتحسين م/إب\nفرع مديرية الحزم", 300.0f, 42.0f, 240.0f, 92.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "END", 1.35f, 0.0f, 20939521, null);
                            designElementEntityArr6[2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية", 55.0f, 46.0f, 170.0f, 28.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33522433, null);
                            designElementEntityArr6[3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "NO: {رقم المستند}\nالتاريخ الهجري: {التاريخ الهجري}\nالموافق: {التاريخ الميلادي}", 55.0f, 88.0f, 220.0f, 72.0f, 0.0f, 0.0f, 1, "AMIRI", 12.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.45f, 0.0f, 25150209, null);
                            designElementEntityArr6[4] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "LINE", null, 42.0f, 172.0f, 511.0f, 4.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, null, "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32766729, null);
                            designElementEntityArr6[i3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", ModelsKt.displayName(this.$type), 160.0f, 190.0f, 275.0f, 40.0f, 0.0f, 0.0f, 3, "AMIRI", 22.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29328129, null);
                            designElementEntityArr6[i2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "اسم المستفيد / مقدم الطلب: {اسم المستفيد}\nالغرض والبيان: {الغرض}\nالمبلغ: {المبلغ}\nالمبلغ كتابةً: {المبلغ كتابة}", 70.0f, 260.0f, 455.0f, 150.0f, 0.0f, 0.0f, 3, "AMIRI", 15.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.7f, 0.0f, 20955905, null);
                            designElementEntityArr6[7] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 62.0f, 242.0f, 471.0f, 190.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, "#FAFCFD", "#B48A3A", 1.5f, false, false, null, 0.0f, 0.0f, 32635657, null);
                            designElementEntityArr6[i] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "البند المالي: {البند المالي}\nمركز التكلفة: {مركز التكلفة}\nمصدر التمويل: {مصدر التمويل}", 70.0f, 455.0f, 455.0f, 92.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.55f, 0.0f, 20955905, null);
                            long id6 = documentDesignEntity3.getId();
                            i6 = WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()];
                            if (i6 == 1) {
                                str = "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................";
                            } else if (i6 != 2) {
                                str = "مقدم الطلب\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                            } else {
                                str = "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                            }
                            String str8 = str;
                            if (this.$type == DocumentType.ORDER) {
                                f = 330.0f;
                            } else {
                                f = 55.0f;
                            }
                            designElementEntityArr6[9] = new DesignElementEntity(0L, id6, "TEXT", str8, f, 690.0f, 225.0f, 105.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.5f, 0.0f, 25150209, null);
                            designElementEntityArr6[10] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "طبع بواسطة نظام مالية فرع صندوق النظافة والتحسين مديرية الحزم", 55.0f, 805.0f, 485.0f, 22.0f, 0.0f, 0.0f, 3, "AMIRI", 9.0f, "#555555", false, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29344513, null);
                            List listListOf6 = CollectionsKt.listOf((Object[]) designElementEntityArr6);
                            List list14 = listListOf6;
                            masrofViewModel2 = MasrofViewModel.this;
                            iterable = list14;
                            it = list14.iterator();
                            list2 = listListOf6;
                            list3 = list;
                            documentDesignEntity4 = documentDesignEntity3;
                            i7 = 0;
                            str2 = "#123B5D";
                            if (it.hasNext()) {
                                Object next6 = it.next();
                                DesignElementEntity designElementEntity6 = (DesignElementEntity) next6;
                                MasrofRepository masrofRepository11 = masrofViewModel2.repository;
                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                this.L$1 = documentDesignEntity4;
                                this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                                this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                                this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                                this.L$5 = SpillingKt.nullOutSpilledVariable(iterable);
                                this.L$6 = masrofViewModel2;
                                this.L$7 = it;
                                this.L$8 = SpillingKt.nullOutSpilledVariable(next6);
                                this.L$9 = SpillingKt.nullOutSpilledVariable(designElementEntity6);
                                this.I$0 = i7;
                                this.I$1 = 0;
                                i8 = i3;
                                this.label = i8;
                            } else {
                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity4);
                                this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                                this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                                this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                                this.L$5 = null;
                                this.L$6 = null;
                                this.L$7 = null;
                                this.L$8 = null;
                                this.L$9 = null;
                                this.label = i2;
                                objDesignElements2 = MasrofViewModel.this.repository.designElements(documentDesignEntity4.getId(), this);
                            }
                        } else {
                            List<DesignElementEntity> list15 = list;
                            documentType2 = this.$type;
                            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list15, 10));
                            while (r3.hasNext()) {
                                if (!Intrinsics.areEqual(designElementEntityCopy$default.getType(), "TEXT")) {
                                    i5 = WhenMappings.$EnumSwitchMapping$0[documentType2.ordinal()];
                                    if (i5 == 1) {
                                        designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................", 330.0f, 690.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554375, null);
                                    } else if (i5 == 2) {
                                        designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................", 55.0f, 650.0f, 0.0f, 125.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554247, null);
                                    }
                                }
                                arrayList.add(designElementEntityCopy$default);
                            }
                            arrayList2 = arrayList;
                            if (!Intrinsics.areEqual(arrayList2, list)) {
                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                this.L$1 = documentDesignEntity3;
                                this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                                this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                                this.label = 7;
                                if (MasrofViewModel.this.repository.replaceDesignElements(documentDesignEntity3.getId(), arrayList2, this) != coroutine_suspended) {
                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                                    this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity3);
                                    this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                                    this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                                    this.label = i;
                                    objDesignElements3 = MasrofViewModel.this.repository.designElements(documentDesignEntity3.getId(), this);
                                }
                                break;
                            }
                            MasrofViewModel.this.getDesignElements().setValue(list);
                            return Unit.INSTANCE;
                        }
                        break;
                    }
                    return coroutine_suspended;
                case 4:
                    documentDesignEntity = (DocumentDesignEntity) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    objDesignElements = obj;
                    i = 8;
                    i2 = 6;
                    i3 = 5;
                    documentDesignEntity3 = documentDesignEntity;
                    list = (List) objDesignElements;
                    if (list.isEmpty()) {
                        DesignElementEntity[] designElementEntityArr7 = new DesignElementEntity[11];
                        designElementEntityArr7[0] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 24.0f, 24.0f, 547.0f, 794.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, "#FFFFFF", "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32635657, null);
                        designElementEntityArr7[1] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية\nوزارة الإدارة والتنمية المحلية والريفية\nصندوق النظافة والتحسين م/إب\nفرع مديرية الحزم", 300.0f, 42.0f, 240.0f, 92.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "END", 1.35f, 0.0f, 20939521, null);
                        designElementEntityArr7[2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "الجمهورية اليمنية", 55.0f, 46.0f, 170.0f, 28.0f, 0.0f, 0.0f, 1, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33522433, null);
                        designElementEntityArr7[3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "NO: {رقم المستند}\nالتاريخ الهجري: {التاريخ الهجري}\nالموافق: {التاريخ الميلادي}", 55.0f, 88.0f, 220.0f, 72.0f, 0.0f, 0.0f, 1, "AMIRI", 12.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.45f, 0.0f, 25150209, null);
                        designElementEntityArr7[4] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "LINE", null, 42.0f, 172.0f, 511.0f, 4.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, null, "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32766729, null);
                        designElementEntityArr7[i3] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", ModelsKt.displayName(this.$type), 160.0f, 190.0f, 275.0f, 40.0f, 0.0f, 0.0f, 3, "AMIRI", 22.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29328129, null);
                        designElementEntityArr7[i2] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "اسم المستفيد / مقدم الطلب: {اسم المستفيد}\nالغرض والبيان: {الغرض}\nالمبلغ: {المبلغ}\nالمبلغ كتابةً: {المبلغ كتابة}", 70.0f, 260.0f, 455.0f, 150.0f, 0.0f, 0.0f, 3, "AMIRI", 15.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.7f, 0.0f, 20955905, null);
                        designElementEntityArr7[7] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "RECT", null, 62.0f, 242.0f, 471.0f, 190.0f, 0.0f, 0.0f, 2, null, 0.0f, null, false, false, false, "#FAFCFD", "#B48A3A", 1.5f, false, false, null, 0.0f, 0.0f, 32635657, null);
                        designElementEntityArr7[i] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "البند المالي: {البند المالي}\nمركز التكلفة: {مركز التكلفة}\nمصدر التمويل: {مصدر التمويل}", 70.0f, 455.0f, 455.0f, 92.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, "END", 1.55f, 0.0f, 20955905, null);
                        long id7 = documentDesignEntity3.getId();
                        i6 = WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()];
                        if (i6 == 1) {
                            str = "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................";
                        } else if (i6 != 2) {
                            str = "مقدم الطلب\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                        } else {
                            str = "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................";
                        }
                        String str9 = str;
                        if (this.$type == DocumentType.ORDER) {
                            f = 330.0f;
                        } else {
                            f = 55.0f;
                        }
                        designElementEntityArr7[9] = new DesignElementEntity(0L, id7, "TEXT", str9, f, 690.0f, 225.0f, 105.0f, 0.0f, 0.0f, 3, "AMIRI", 14.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.5f, 0.0f, 25150209, null);
                        designElementEntityArr7[10] = new DesignElementEntity(0L, documentDesignEntity3.getId(), "TEXT", "طبع بواسطة نظام مالية فرع صندوق النظافة والتحسين مديرية الحزم", 55.0f, 805.0f, 485.0f, 22.0f, 0.0f, 0.0f, 3, "AMIRI", 9.0f, "#555555", false, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29344513, null);
                        List listListOf7 = CollectionsKt.listOf((Object[]) designElementEntityArr7);
                        List list16 = listListOf7;
                        masrofViewModel2 = MasrofViewModel.this;
                        iterable = list16;
                        it = list16.iterator();
                        list2 = listListOf7;
                        list3 = list;
                        documentDesignEntity4 = documentDesignEntity3;
                        i7 = 0;
                        str2 = "#123B5D";
                        if (it.hasNext()) {
                            Object next7 = it.next();
                            DesignElementEntity designElementEntity7 = (DesignElementEntity) next7;
                            MasrofRepository masrofRepository12 = masrofViewModel2.repository;
                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                            this.L$1 = documentDesignEntity4;
                            this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                            this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                            this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                            this.L$5 = SpillingKt.nullOutSpilledVariable(iterable);
                            this.L$6 = masrofViewModel2;
                            this.L$7 = it;
                            this.L$8 = SpillingKt.nullOutSpilledVariable(next7);
                            this.L$9 = SpillingKt.nullOutSpilledVariable(designElementEntity7);
                            this.I$0 = i7;
                            this.I$1 = 0;
                            i8 = i3;
                            this.label = i8;
                            break;
                        } else {
                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity4);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                            this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                            this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                            this.L$5 = null;
                            this.L$6 = null;
                            this.L$7 = null;
                            this.L$8 = null;
                            this.L$9 = null;
                            this.label = i2;
                            objDesignElements2 = MasrofViewModel.this.repository.designElements(documentDesignEntity4.getId(), this);
                            break;
                        }
                        return coroutine_suspended;
                    }
                    List<DesignElementEntity> list17 = list;
                    documentType2 = this.$type;
                    arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list17, 10));
                    while (r3.hasNext()) {
                        if (!Intrinsics.areEqual(designElementEntityCopy$default.getType(), "TEXT")) {
                            i5 = WhenMappings.$EnumSwitchMapping$0[documentType2.ordinal()];
                            if (i5 == 1) {
                                designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "المسؤول المالي\nالاسم: ................................\nالتوقيع: .................................", 330.0f, 690.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554375, null);
                            } else if (i5 == 2) {
                                designElementEntityCopy$default = DesignElementEntity.copy$default(designElementEntityCopy$default, 0L, 0L, null, "واقـر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهد على ذلك.\nالمستلم\nالاسم: {اسم المستفيد}\nالتوقيع: .................................", 55.0f, 650.0f, 0.0f, 125.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554247, null);
                            }
                        }
                        arrayList.add(designElementEntityCopy$default);
                    }
                    arrayList2 = arrayList;
                    if (!Intrinsics.areEqual(arrayList2, list)) {
                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                        this.L$1 = documentDesignEntity3;
                        this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                        this.label = 7;
                        if (MasrofViewModel.this.repository.replaceDesignElements(documentDesignEntity3.getId(), arrayList2, this) != coroutine_suspended) {
                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity3);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                            this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                            this.label = i;
                            objDesignElements3 = MasrofViewModel.this.repository.designElements(documentDesignEntity3.getId(), this);
                            break;
                        }
                        return coroutine_suspended;
                    }
                    MasrofViewModel.this.getDesignElements().setValue(list);
                    return Unit.INSTANCE;
                case 5:
                    i7 = this.I$0;
                    it = (Iterator) this.L$7;
                    masrofViewModel2 = (MasrofViewModel) this.L$6;
                    iterable = (Iterable) this.L$5;
                    list2 = (List) this.L$4;
                    str2 = (String) this.L$3;
                    list3 = (List) this.L$2;
                    documentDesignEntity4 = (DocumentDesignEntity) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    i8 = 5;
                    i2 = 6;
                    i3 = i8;
                    if (it.hasNext()) {
                        Object next8 = it.next();
                        DesignElementEntity designElementEntity8 = (DesignElementEntity) next8;
                        MasrofRepository masrofRepository13 = masrofViewModel2.repository;
                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                        this.L$1 = documentDesignEntity4;
                        this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                        this.L$5 = SpillingKt.nullOutSpilledVariable(iterable);
                        this.L$6 = masrofViewModel2;
                        this.L$7 = it;
                        this.L$8 = SpillingKt.nullOutSpilledVariable(next8);
                        this.L$9 = SpillingKt.nullOutSpilledVariable(designElementEntity8);
                        this.I$0 = i7;
                        this.I$1 = 0;
                        i8 = i3;
                        this.label = i8;
                        break;
                    } else {
                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity4);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(list3);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(str2);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(list2);
                        this.L$5 = null;
                        this.L$6 = null;
                        this.L$7 = null;
                        this.L$8 = null;
                        this.L$9 = null;
                        this.label = i2;
                        objDesignElements2 = MasrofViewModel.this.repository.designElements(documentDesignEntity4.getId(), this);
                        break;
                    }
                    return coroutine_suspended;
                case 6:
                    ResultKt.throwOnFailure(obj);
                    objDesignElements2 = obj;
                    list = (List) objDesignElements2;
                    MasrofViewModel.this.getDesignElements().setValue(list);
                    return Unit.INSTANCE;
                case 7:
                    arrayList2 = (List) this.L$3;
                    list = (List) this.L$2;
                    documentDesignEntity3 = (DocumentDesignEntity) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    i = 8;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope2);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity3);
                    this.L$2 = SpillingKt.nullOutSpilledVariable(list);
                    this.L$3 = SpillingKt.nullOutSpilledVariable(arrayList2);
                    this.label = i;
                    objDesignElements3 = MasrofViewModel.this.repository.designElements(documentDesignEntity3.getId(), this);
                    break;
                case 8:
                    ResultKt.throwOnFailure(obj);
                    objDesignElements3 = obj;
                    list = (List) objDesignElements3;
                    MasrofViewModel.this.getDesignElements().setValue(list);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void loadDesign(DocumentType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40721(type, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$updateDesign$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$updateDesign$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {106, 106}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DocumentDesignEntity $design;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40821(DocumentDesignEntity documentDesignEntity, Continuation<? super C40821> continuation) {
            super(2, continuation);
            this.$design = documentDesignEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40821(this.$design, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x007d, code lost:
        
            if (r0 == r1) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            MutableStateFlow activeDesign;
            Object design;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MasrofRepository masrofRepository = MasrofViewModel.this.repository;
                DocumentDesignEntity documentDesignEntity = this.$design;
                this.label = 1;
                if (masrofRepository.saveDesign(documentDesignEntity.copy((199 & 1) != 0 ? documentDesignEntity.id : 0L, (199 & 2) != 0 ? documentDesignEntity.documentType : null, (199 & 4) != 0 ? documentDesignEntity.name : null, (199 & 8) != 0 ? documentDesignEntity.pageWidth : 0.0f, (199 & 16) != 0 ? documentDesignEntity.pageHeight : 0.0f, (199 & 32) != 0 ? documentDesignEntity.backgroundColor : null, (199 & 64) != 0 ? documentDesignEntity.backgroundImageUri : null, (199 & 128) != 0 ? documentDesignEntity.updatedAt : System.currentTimeMillis(), (199 & 256) != 0 ? documentDesignEntity.orientation : null, (199 & 512) != 0 ? documentDesignEntity.marginLeft : 0.0f, (199 & 1024) != 0 ? documentDesignEntity.marginTop : 0.0f, (199 & 2048) != 0 ? documentDesignEntity.marginRight : 0.0f, (199 & 4096) != 0 ? documentDesignEntity.marginBottom : 0.0f), this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                MutableStateFlow mutableStateFlow = (MutableStateFlow) this.L$0;
                ResultKt.throwOnFailure(obj);
                activeDesign = mutableStateFlow;
                design = obj;
            }
            activeDesign.setValue(design);
            return Unit.INSTANCE;
            activeDesign = MasrofViewModel.this.getActiveDesign();
            this.L$0 = activeDesign;
            this.label = 2;
            design = MasrofViewModel.this.repository.getDesign(DocumentType.valueOf(this.$design.getDocumentType()), this);
        }
    }

    public final void updateDesign(DocumentDesignEntity design) {
        Intrinsics.checkNotNullParameter(design, "design");
        this.activeDesign.setValue(design);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40821(design, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$applyAiCommands$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$applyAiCommands$1", m938f = "MasrofViewModel.kt", m939i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4}, m940l = {112, 113, 114, 115, 115, 118}, m941m = "invokeSuspend", m942n = {"$this$forEach$iv", "element$iv", "command", "e", "$i$f$forEach", "$i$a$-forEach-MasrofViewModel$applyAiCommands$1$1", "$i$a$-let-MasrofViewModel$applyAiCommands$1$1$2", "$this$forEach$iv", "element$iv", "command", "it", "$i$f$forEach", "$i$a$-forEach-MasrofViewModel$applyAiCommands$1$1", "$i$a$-let-MasrofViewModel$applyAiCommands$1$1$4", "$this$forEach$iv", "element$iv", "command", "$i$f$forEach", "$i$a$-forEach-MasrofViewModel$applyAiCommands$1$1", "$this$forEach$iv", "element$iv", "command", "d", "$i$f$forEach", "$i$a$-forEach-MasrofViewModel$applyAiCommands$1$1", "$i$a$-let-MasrofViewModel$applyAiCommands$1$1$6", "$this$forEach$iv", "element$iv", "command", "d", "$i$f$forEach", "$i$a$-forEach-MasrofViewModel$applyAiCommands$1$1", "$i$a$-let-MasrofViewModel$applyAiCommands$1$1$6"}, m943s = {"L$0", "L$3", "L$4", "L$5", "I$0", "I$1", "I$2", "L$0", "L$3", "L$4", "L$5", "I$0", "I$1", "I$2", "L$0", "L$3", "L$4", "I$0", "I$1", "L$0", "L$3", "L$4", "L$5", "I$0", "I$1", "I$2", "L$0", "L$3", "L$4", "L$5", "I$0", "I$1", "I$2"})
    static final class C40581 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<AiLayoutAssistant.Command> $commands;
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        final /* synthetic */ MasrofViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40581(List<AiLayoutAssistant.Command> list, MasrofViewModel masrofViewModel, Continuation<? super C40581> continuation) {
            super(2, continuation);
            this.$commands = list;
            this.this$0 = masrofViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C40581(this.$commands, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40581) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:101:0x02cd  */
        /* JADX WARN: Code duplicated, block: B:103:0x02d7  */
        /* JADX WARN: Code duplicated, block: B:104:0x02da  */
        /* JADX WARN: Code duplicated, block: B:107:0x02e4  */
        /* JADX WARN: Code duplicated, block: B:110:0x02f0  */
        /* JADX WARN: Code duplicated, block: B:111:0x02f7  */
        /* JADX WARN: Code duplicated, block: B:114:0x02ff  */
        /* JADX WARN: Code duplicated, block: B:117:0x030b  */
        /* JADX WARN: Code duplicated, block: B:118:0x0310  */
        /* JADX WARN: Code duplicated, block: B:121:0x031a  */
        /* JADX WARN: Code duplicated, block: B:122:0x031f  */
        /* JADX WARN: Code duplicated, block: B:125:0x0337  */
        /* JADX WARN: Code duplicated, block: B:126:0x0339  */
        /* JADX WARN: Code duplicated, block: B:129:0x034f  */
        /* JADX WARN: Code duplicated, block: B:134:0x036b  */
        /* JADX WARN: Code duplicated, block: B:135:0x0370  */
        /* JADX WARN: Code duplicated, block: B:138:0x0379  */
        /* JADX WARN: Code duplicated, block: B:139:0x037e  */
        /* JADX WARN: Code duplicated, block: B:142:0x0388  */
        /* JADX WARN: Code duplicated, block: B:145:0x0392  */
        /* JADX WARN: Code duplicated, block: B:158:0x040d  */
        /* JADX WARN: Code duplicated, block: B:15:0x00d6  */
        /* JADX WARN: Code duplicated, block: B:169:0x0438  */
        /* JADX WARN: Code duplicated, block: B:170:0x043d  */
        /* JADX WARN: Code duplicated, block: B:173:0x0449  */
        /* JADX WARN: Code duplicated, block: B:174:0x044e  */
        /* JADX WARN: Code duplicated, block: B:177:0x045a  */
        /* JADX WARN: Code duplicated, block: B:178:0x045f  */
        /* JADX WARN: Code duplicated, block: B:181:0x046b  */
        /* JADX WARN: Code duplicated, block: B:182:0x0470  */
        /* JADX WARN: Code duplicated, block: B:185:0x047c  */
        /* JADX WARN: Code duplicated, block: B:186:0x0481  */
        /* JADX WARN: Code duplicated, block: B:189:0x048d  */
        /* JADX WARN: Code duplicated, block: B:192:0x0499  */
        /* JADX WARN: Code duplicated, block: B:193:0x049e  */
        /* JADX WARN: Code duplicated, block: B:196:0x04aa  */
        /* JADX WARN: Code duplicated, block: B:199:0x04b6  */
        /* JADX WARN: Code duplicated, block: B:203:0x0512  */
        /* JADX WARN: Code duplicated, block: B:211:0x00ec A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:212:0x0160 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:213:0x0168 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:214:0x0172 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:215:0x017c A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:216:0x02a1 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:217:0x02ab A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:218:0x02b5 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:219:0x03e5 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:220:0x03ef A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:221:0x0516 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:222:0x00f5 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:223:0x0129 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:224:0x0542 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:225:0x02bf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:226:0x0542 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:227:0x03f9 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:228:0x042e A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:229:0x0542 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:230:0x03f9 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:231:0x0186 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:233:0x03f9 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:234:0x02bf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:235:0x02bf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:236:0x03f9 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:237:0x03f9 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:239:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:240:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:241:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:243:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:244:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:245:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:246:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:248:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:249:0x00cf A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:24:0x0109  */
        /* JADX WARN: Code duplicated, block: B:251:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:252:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:253:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:254:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:255:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:257:0x0124 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:258:0x011b A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:261:0x0103 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:264:0x0365 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:266:0x0349 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:268:0x0428 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:269:0x041f A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:272:0x0407 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:36:0x015c  */
        /* JADX WARN: Code duplicated, block: B:53:0x019d  */
        /* JADX WARN: Code duplicated, block: B:54:0x01a2  */
        /* JADX WARN: Code duplicated, block: B:57:0x01ae  */
        /* JADX WARN: Code duplicated, block: B:58:0x01b3  */
        /* JADX WARN: Code duplicated, block: B:61:0x01bf  */
        /* JADX WARN: Code duplicated, block: B:64:0x01cb  */
        /* JADX WARN: Code duplicated, block: B:65:0x01d0  */
        /* JADX WARN: Code duplicated, block: B:68:0x01dc  */
        /* JADX WARN: Code duplicated, block: B:69:0x01e1  */
        /* JADX WARN: Code duplicated, block: B:72:0x01ed  */
        /* JADX WARN: Code duplicated, block: B:73:0x01f2  */
        /* JADX WARN: Code duplicated, block: B:76:0x01fe  */
        /* JADX WARN: Code duplicated, block: B:77:0x0203  */
        /* JADX WARN: Code duplicated, block: B:80:0x020f  */
        /* JADX WARN: Code duplicated, block: B:84:0x0250  */
        /* JADX WARN: Code duplicated, block: B:88:0x0296  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:88:0x0296 -> B:89:0x0297). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:228:0x042e
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r46) {
            /*
                Method dump skipped, instruction units count: 1414
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mohammedalhzmi.masrofmanager.p010ui.MasrofViewModel.C40581.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void applyAiCommands(List<AiLayoutAssistant.Command> commands) {
        Intrinsics.checkNotNullParameter(commands, "commands");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40581(commands, this, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$addDesignElement$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$addDesignElement$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {121, 121}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DesignElementEntity $element;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40561(DesignElementEntity designElementEntity, Continuation<? super C40561> continuation) {
            super(2, continuation);
            this.$element = designElementEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40561(this.$element, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0095, code lost:
        
            if (r0 == r1) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            MutableStateFlow designElements;
            Object objDesignElements;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MasrofViewModel.this.repository.addDesignElement(DesignElementEntity.copy$default(this.$element, 0L, MasrofViewModel.this.activeDesignId, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554429, null), this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                MutableStateFlow mutableStateFlow = (MutableStateFlow) this.L$0;
                ResultKt.throwOnFailure(obj);
                designElements = mutableStateFlow;
                objDesignElements = obj;
            }
            designElements.setValue(objDesignElements);
            return Unit.INSTANCE;
            designElements = MasrofViewModel.this.getDesignElements();
            this.L$0 = designElements;
            this.label = 2;
            objDesignElements = MasrofViewModel.this.repository.designElements(MasrofViewModel.this.activeDesignId, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$updateDesignElement$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$updateDesignElement$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {122, 122}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DesignElementEntity $element;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40831(DesignElementEntity designElementEntity, Continuation<? super C40831> continuation) {
            super(2, continuation);
            this.$element = designElementEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40831(this.$element, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableStateFlow mutableStateFlow;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MasrofViewModel.this.repository.updateDesignElement(this.$element, this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableStateFlow = (MutableStateFlow) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            mutableStateFlow.setValue(obj);
            return Unit.INSTANCE;
            MutableStateFlow<List<DesignElementEntity>> designElements = MasrofViewModel.this.getDesignElements();
            this.L$0 = designElements;
            this.label = 2;
            Object objDesignElements = MasrofViewModel.this.repository.designElements(MasrofViewModel.this.activeDesignId, this);
            if (objDesignElements != coroutine_suspended) {
                obj = objDesignElements;
                mutableStateFlow = designElements;
                mutableStateFlow.setValue(obj);
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
    }

    public final void addDesignElement(DesignElementEntity element) {
        Intrinsics.checkNotNullParameter(element, "element");
        rememberChange();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40561(element, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$deleteDesignElement$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$deleteDesignElement$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {123, 123}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40651 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DesignElementEntity $element;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40651(DesignElementEntity designElementEntity, Continuation<? super C40651> continuation) {
            super(2, continuation);
            this.$element = designElementEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40651(this.$element, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40651) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableStateFlow mutableStateFlow;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MasrofViewModel.this.repository.deleteDesignElement(this.$element, this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableStateFlow = (MutableStateFlow) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            mutableStateFlow.setValue(obj);
            return Unit.INSTANCE;
            MutableStateFlow<List<DesignElementEntity>> designElements = MasrofViewModel.this.getDesignElements();
            this.L$0 = designElements;
            this.label = 2;
            Object objDesignElements = MasrofViewModel.this.repository.designElements(MasrofViewModel.this.activeDesignId, this);
            if (objDesignElements != coroutine_suspended) {
                obj = objDesignElements;
                mutableStateFlow = designElements;
                mutableStateFlow.setValue(obj);
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
    }

    public final void updateDesignElement(DesignElementEntity element) {
        Intrinsics.checkNotNullParameter(element, "element");
        rememberChange();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40831(element, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$moveLayer$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$moveLayer$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {124, 124}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $delta;
        final /* synthetic */ DesignElementEntity $element;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40731(DesignElementEntity designElementEntity, int i, Continuation<? super C40731> continuation) {
            super(2, continuation);
            this.$element = designElementEntity;
            this.$delta = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40731(this.$element, this.$delta, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableStateFlow mutableStateFlow;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MasrofViewModel.this.repository.setDesignLayer(this.$element.getId(), RangesKt.coerceAtLeast(this.$element.getZIndex() + this.$delta, 0), this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableStateFlow = (MutableStateFlow) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            mutableStateFlow.setValue(obj);
            return Unit.INSTANCE;
            MutableStateFlow<List<DesignElementEntity>> designElements = MasrofViewModel.this.getDesignElements();
            this.L$0 = designElements;
            this.label = 2;
            Object objDesignElements = MasrofViewModel.this.repository.designElements(MasrofViewModel.this.activeDesignId, this);
            if (objDesignElements != coroutine_suspended) {
                obj = objDesignElements;
                mutableStateFlow = designElements;
                mutableStateFlow.setValue(obj);
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
    }

    public final void deleteDesignElement(DesignElementEntity element) {
        Intrinsics.checkNotNullParameter(element, "element");
        rememberChange();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40651(element, null), 2, null);
    }

    public final void moveLayer(DesignElementEntity element, int delta) {
        Intrinsics.checkNotNullParameter(element, "element");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40731(element, delta, null), 2, null);
    }

    public final void copyElement(DesignElementEntity element) {
        Intrinsics.checkNotNullParameter(element, "element");
        this.clipboard = DesignElementEntity.copy$default(element, 0L, 0L, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554430, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$undo$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$undo$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {127, 127}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<DesignElementEntity> $previous;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40811(List<DesignElementEntity> list, Continuation<? super C40811> continuation) {
            super(2, continuation);
            this.$previous = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40811(this.$previous, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableStateFlow mutableStateFlow;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MasrofViewModel.this.repository.replaceDesignElements(MasrofViewModel.this.activeDesignId, this.$previous, this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableStateFlow = (MutableStateFlow) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            mutableStateFlow.setValue(obj);
            return Unit.INSTANCE;
            MutableStateFlow<List<DesignElementEntity>> designElements = MasrofViewModel.this.getDesignElements();
            this.L$0 = designElements;
            this.label = 2;
            Object objDesignElements = MasrofViewModel.this.repository.designElements(MasrofViewModel.this.activeDesignId, this);
            if (objDesignElements != coroutine_suspended) {
                obj = objDesignElements;
                mutableStateFlow = designElements;
                mutableStateFlow.setValue(obj);
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
    }

    public final void pasteElement() {
        Integer num;
        DesignElementEntity designElementEntity = this.clipboard;
        if (designElementEntity != null) {
            float x = designElementEntity.getX() + 16.0f;
            float y = designElementEntity.getY() + 16.0f;
            Iterator<T> it = this.designElements.getValue().iterator();
            if (it.hasNext()) {
                Integer numValueOf = Integer.valueOf(((DesignElementEntity) it.next()).getZIndex());
                while (it.hasNext()) {
                    Integer numValueOf2 = Integer.valueOf(((DesignElementEntity) it.next()).getZIndex());
                    if (numValueOf.compareTo(numValueOf2) < 0) {
                        numValueOf = numValueOf2;
                    }
                }
                num = numValueOf;
            } else {
                num = null;
            }
            Integer num2 = num;
            addDesignElement(DesignElementEntity.copy$default(designElementEntity, 0L, 0L, null, null, x, y, 0.0f, 0.0f, 0.0f, 0.0f, (num2 != null ? num2.intValue() : 0) + 1, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33553359, null));
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$redo$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$redo$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {128, 128}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40751 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<DesignElementEntity> $next;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40751(List<DesignElementEntity> list, Continuation<? super C40751> continuation) {
            super(2, continuation);
            this.$next = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40751(this.$next, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableStateFlow mutableStateFlow;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MasrofViewModel.this.repository.replaceDesignElements(MasrofViewModel.this.activeDesignId, this.$next, this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableStateFlow = (MutableStateFlow) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            mutableStateFlow.setValue(obj);
            return Unit.INSTANCE;
            MutableStateFlow<List<DesignElementEntity>> designElements = MasrofViewModel.this.getDesignElements();
            this.L$0 = designElements;
            this.label = 2;
            Object objDesignElements = MasrofViewModel.this.repository.designElements(MasrofViewModel.this.activeDesignId, this);
            if (objDesignElements != coroutine_suspended) {
                obj = objDesignElements;
                mutableStateFlow = designElements;
                mutableStateFlow.setValue(obj);
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
    }

    public final void undo() {
        if (this.undoStack.isEmpty()) {
            return;
        }
        List<DesignElementEntity> listSnapshot = snapshot();
        List<DesignElementEntity> listRemoveLast = this.undoStack.removeLast();
        this.redoStack.addLast(listSnapshot);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40811(listRemoveLast, null), 2, null);
    }

    public final void redo() {
        if (this.redoStack.isEmpty()) {
            return;
        }
        List<DesignElementEntity> listSnapshot = snapshot();
        List<DesignElementEntity> listRemoveLast = this.redoStack.removeLast();
        this.undoStack.addLast(listSnapshot);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40751(listRemoveLast, null), 2, null);
    }

    public final Object ensureDefaultAdmin(Continuation<? super Unit> continuation) {
        Object objArchiveOlderThan = this.repository.archiveOlderThan(System.currentTimeMillis() - 31536000000L, System.currentTimeMillis(), continuation);
        return objArchiveOlderThan == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objArchiveOlderThan : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:47:0x00e1 A[Catch: all -> 0x005d, TryCatch #0 {all -> 0x005d, blocks: (B:13:0x003c, B:41:0x00ce, B:43:0x00d8, B:53:0x00f6, B:55:0x00fe, B:60:0x0108, B:62:0x0117, B:64:0x011d, B:47:0x00e1, B:49:0x00eb, B:18:0x0057, B:34:0x009f, B:37:0x00a8, B:25:0x006d, B:27:0x007c, B:30:0x0084), top: B:73:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00eb A[Catch: all -> 0x005d, TryCatch #0 {all -> 0x005d, blocks: (B:13:0x003c, B:41:0x00ce, B:43:0x00d8, B:53:0x00f6, B:55:0x00fe, B:60:0x0108, B:62:0x0117, B:64:0x011d, B:47:0x00e1, B:49:0x00eb, B:18:0x0057, B:34:0x009f, B:37:0x00a8, B:25:0x006d, B:27:0x007c, B:30:0x0084), top: B:73:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:52:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:59:0x0107  */
    /* JADX WARN: Code duplicated, block: B:62:0x0117 A[Catch: all -> 0x005d, TryCatch #0 {all -> 0x005d, blocks: (B:13:0x003c, B:41:0x00ce, B:43:0x00d8, B:53:0x00f6, B:55:0x00fe, B:60:0x0108, B:62:0x0117, B:64:0x011d, B:47:0x00e1, B:49:0x00eb, B:18:0x0057, B:34:0x009f, B:37:0x00a8, B:25:0x006d, B:27:0x007c, B:30:0x0084), top: B:73:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x011c  */
    /* JADX WARN: Code duplicated, block: B:71:0x0140  */
    /* JADX WARN: Code duplicated, block: B:75:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object restoreRememberedUser(Context context, Continuation<? super AuthenticatedUser> continuation) {
        C40791 c40791;
        Object objM7781constructorimpl;
        String uid;
        int i;
        Context context2;
        String str;
        MasrofViewModel masrofViewModel;
        String str2;
        Object obj;
        FirebaseUser currentUser;
        String email;
        String str3;
        Object obj2;
        String str4;
        Object obj3;
        String string;
        String string2;
        if (continuation instanceof C40791) {
            c40791 = (C40791) continuation;
            if ((c40791.label & Integer.MIN_VALUE) != 0) {
                c40791.label -= Integer.MIN_VALUE;
            } else {
                c40791 = new C40791(continuation);
            }
        } else {
            c40791 = new C40791(continuation);
        }
        Object objCurrentProfile = c40791.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c40791.label;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    int i3 = c40791.I$0;
                    str = (String) c40791.L$2;
                    MasrofViewModel masrofViewModel2 = (MasrofViewModel) c40791.L$1;
                    context2 = (Context) c40791.L$0;
                    ResultKt.throwOnFailure(objCurrentProfile);
                    i = i3;
                    this = masrofViewModel2;
                } else {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    int i4 = c40791.I$0;
                    str2 = (String) c40791.L$3;
                    masrofViewModel = (MasrofViewModel) c40791.L$1;
                    ResultKt.throwOnFailure(objCurrentProfile);
                }
                Map map = (Map) objCurrentProfile;
                obj = map.get(HintConstants.AUTOFILL_HINT_USERNAME);
                if (obj != null || (email = obj.toString()) == null) {
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (currentUser != null) {
                        email = currentUser.getEmail();
                    } else {
                        email = null;
                    }
                    if (email == null) {
                        email = "";
                    }
                }
                str3 = email;
                obj2 = map.get("fullName");
                if (obj2 != null || (string2 = obj2.toString()) == null) {
                    str4 = str3;
                } else {
                    str4 = string2;
                }
                long jHashCode = str2.hashCode();
                obj3 = map.get("role");
                if (obj3 != null) {
                    string = obj3.toString();
                } else {
                    string = null;
                }
                AuthenticatedUser authenticatedUser = new AuthenticatedUser(jHashCode, str3, str4, masrofViewModel.roleFrom(string));
                UserSession.INSTANCE.setCurrent(authenticatedUser);
                objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser);
                if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
                    return null;
                }
                return objM7781constructorimpl;
            }
            ResultKt.throwOnFailure(objCurrentProfile);
            if (!RememberedLogin.INSTANCE.enabled(context)) {
                return null;
            }
            Result.Companion companion = Result.INSTANCE;
            MasrofViewModel masrofViewModel3 = this;
            FirebaseUser currentUser2 = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser2 != null && (uid = currentUser2.getUid()) != null) {
                FirebaseCloudSync firebaseCloudSync = this.cloud;
                c40791.L$0 = SpillingKt.nullOutSpilledVariable(context);
                c40791.L$1 = this;
                c40791.L$2 = uid;
                i = 0;
                c40791.I$0 = 0;
                c40791.label = 1;
                Object objAuthorizeCurrentDevice = firebaseCloudSync.authorizeCurrentDevice(context, c40791);
                if (objAuthorizeCurrentDevice != coroutine_suspended) {
                    context2 = context;
                    str = uid;
                    objCurrentProfile = objAuthorizeCurrentDevice;
                }
                return coroutine_suspended;
            }
            return null;
            FirebaseCloudSync.AccessDecision accessDecision = (FirebaseCloudSync.AccessDecision) objCurrentProfile;
            if (!accessDecision.getAllowed()) {
                return null;
            }
            this.isManagerSession = accessDecision.isManager();
            FirebaseCloudSync firebaseCloudSync2 = this.cloud;
            c40791.L$0 = SpillingKt.nullOutSpilledVariable(context2);
            c40791.L$1 = this;
            c40791.L$2 = SpillingKt.nullOutSpilledVariable(accessDecision);
            c40791.L$3 = str;
            c40791.I$0 = i;
            c40791.label = 2;
            objCurrentProfile = firebaseCloudSync2.currentProfile(c40791);
            if (objCurrentProfile != coroutine_suspended) {
                String str5 = str;
                masrofViewModel = this;
                str2 = str5;
                Map map2 = (Map) objCurrentProfile;
                obj = map2.get(HintConstants.AUTOFILL_HINT_USERNAME);
                if (obj != null) {
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (currentUser != null) {
                        email = currentUser.getEmail();
                    } else {
                        email = null;
                    }
                    if (email == null) {
                        email = "";
                    }
                } else {
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (currentUser != null) {
                        email = currentUser.getEmail();
                    } else {
                        email = null;
                    }
                    if (email == null) {
                        email = "";
                    }
                }
                str3 = email;
                obj2 = map2.get("fullName");
                if (obj2 != null) {
                    str4 = str3;
                } else {
                    str4 = str3;
                }
                long jHashCode2 = str2.hashCode();
                obj3 = map2.get("role");
                if (obj3 != null) {
                    string = obj3.toString();
                } else {
                    string = null;
                }
                AuthenticatedUser authenticatedUser2 = new AuthenticatedUser(jHashCode2, str3, str4, masrofViewModel.roleFrom(string));
                UserSession.INSTANCE.setCurrent(authenticatedUser2);
                objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
                if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
                    return null;
                }
                return objM7781constructorimpl;
            }
            return coroutine_suspended;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object restoreLocalUser(Context context, Continuation<? super AuthenticatedUser> continuation) {
        C40781 c40781;
        String strUsername;
        if (continuation instanceof C40781) {
            c40781 = (C40781) continuation;
            if ((c40781.label & Integer.MIN_VALUE) != 0) {
                c40781.label -= Integer.MIN_VALUE;
            } else {
                c40781 = new C40781(continuation);
            }
        } else {
            c40781 = new C40781(continuation);
        }
        Object objFindActiveUser = c40781.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c40781.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objFindActiveUser);
            if (!RememberedLogin.INSTANCE.enabled(context) || (strUsername = RememberedLogin.INSTANCE.username(context)) == null) {
                return null;
            }
            MasrofRepository masrofRepository = this.repository;
            c40781.L$0 = SpillingKt.nullOutSpilledVariable(context);
            c40781.L$1 = SpillingKt.nullOutSpilledVariable(strUsername);
            c40781.label = 1;
            objFindActiveUser = masrofRepository.findActiveUser(strUsername, c40781);
            if (objFindActiveUser == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objFindActiveUser);
        }
        UserEntity userEntity = (UserEntity) objFindActiveUser;
        if (userEntity == null) {
            return null;
        }
        AuthenticatedUser authenticatedUser = new AuthenticatedUser(userEntity.getId(), userEntity.getUsername(), userEntity.getFullName(), roleFrom(userEntity.getRole()));
        this.isManagerSession = authenticatedUser.getRole() == AppRole.ADMIN;
        UserSession.INSTANCE.setCurrent(authenticatedUser);
        return authenticatedUser;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x03a1  */
    /* JADX WARN: Code duplicated, block: B:103:0x03aa A[Catch: all -> 0x0550, TryCatch #4 {all -> 0x0550, blocks: (B:96:0x038f, B:98:0x039b, B:101:0x03a2, B:103:0x03aa, B:108:0x03b6, B:110:0x03be, B:112:0x03c4, B:114:0x03d9, B:119:0x03e5, B:90:0x0352, B:92:0x035a, B:142:0x053d, B:143:0x054f), top: B:174:0x0352 }] */
    /* JADX WARN: Code duplicated, block: B:107:0x03b4  */
    /* JADX WARN: Code duplicated, block: B:110:0x03be A[Catch: all -> 0x0550, TryCatch #4 {all -> 0x0550, blocks: (B:96:0x038f, B:98:0x039b, B:101:0x03a2, B:103:0x03aa, B:108:0x03b6, B:110:0x03be, B:112:0x03c4, B:114:0x03d9, B:119:0x03e5, B:90:0x0352, B:92:0x035a, B:142:0x053d, B:143:0x054f), top: B:174:0x0352 }] */
    /* JADX WARN: Code duplicated, block: B:111:0x03c3  */
    /* JADX WARN: Code duplicated, block: B:114:0x03d9 A[Catch: all -> 0x0550, TryCatch #4 {all -> 0x0550, blocks: (B:96:0x038f, B:98:0x039b, B:101:0x03a2, B:103:0x03aa, B:108:0x03b6, B:110:0x03be, B:112:0x03c4, B:114:0x03d9, B:119:0x03e5, B:90:0x0352, B:92:0x035a, B:142:0x053d, B:143:0x054f), top: B:174:0x0352 }] */
    /* JADX WARN: Code duplicated, block: B:118:0x03e3  */
    /* JADX WARN: Code duplicated, block: B:122:0x0438  */
    /* JADX WARN: Code duplicated, block: B:125:0x0449 A[Catch: all -> 0x0538, TRY_ENTER, TryCatch #11 {all -> 0x0538, blocks: (B:131:0x04ce, B:125:0x0449, B:127:0x045c, B:126:0x0455), top: B:182:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:126:0x0455 A[Catch: all -> 0x0538, TryCatch #11 {all -> 0x0538, blocks: (B:131:0x04ce, B:125:0x0449, B:127:0x045c, B:126:0x0455), top: B:182:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:129:0x04c8  */
    /* JADX WARN: Code duplicated, block: B:130:0x04ca  */
    /* JADX WARN: Code duplicated, block: B:134:0x0526  */
    /* JADX WARN: Code duplicated, block: B:142:0x053d A[Catch: all -> 0x0550, TRY_ENTER, TryCatch #4 {all -> 0x0550, blocks: (B:96:0x038f, B:98:0x039b, B:101:0x03a2, B:103:0x03aa, B:108:0x03b6, B:110:0x03be, B:112:0x03c4, B:114:0x03d9, B:119:0x03e5, B:90:0x0352, B:92:0x035a, B:142:0x053d, B:143:0x054f), top: B:174:0x0352 }] */
    /* JADX WARN: Code duplicated, block: B:160:0x0574  */
    /* JADX WARN: Code duplicated, block: B:161:0x0576  */
    /* JADX WARN: Code duplicated, block: B:65:0x025b A[Catch: all -> 0x0552, TryCatch #5 {all -> 0x0552, blocks: (B:86:0x0327, B:63:0x0257, B:65:0x025b, B:67:0x0267, B:71:0x028c, B:73:0x0295, B:75:0x02a4, B:74:0x029f, B:80:0x02f7, B:81:0x0302, B:82:0x0303, B:59:0x0246), top: B:175:0x0246 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x0267 A[Catch: all -> 0x0552, TryCatch #5 {all -> 0x0552, blocks: (B:86:0x0327, B:63:0x0257, B:65:0x025b, B:67:0x0267, B:71:0x028c, B:73:0x0295, B:75:0x02a4, B:74:0x029f, B:80:0x02f7, B:81:0x0302, B:82:0x0303, B:59:0x0246), top: B:175:0x0246 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x028a  */
    /* JADX WARN: Code duplicated, block: B:70:0x028b  */
    /* JADX WARN: Code duplicated, block: B:73:0x0295 A[Catch: all -> 0x0552, TryCatch #5 {all -> 0x0552, blocks: (B:86:0x0327, B:63:0x0257, B:65:0x025b, B:67:0x0267, B:71:0x028c, B:73:0x0295, B:75:0x02a4, B:74:0x029f, B:80:0x02f7, B:81:0x0302, B:82:0x0303, B:59:0x0246), top: B:175:0x0246 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x029f A[Catch: all -> 0x0552, TryCatch #5 {all -> 0x0552, blocks: (B:86:0x0327, B:63:0x0257, B:65:0x025b, B:67:0x0267, B:71:0x028c, B:73:0x0295, B:75:0x02a4, B:74:0x029f, B:80:0x02f7, B:81:0x0302, B:82:0x0303, B:59:0x0246), top: B:175:0x0246 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x02ee  */
    /* JADX WARN: Code duplicated, block: B:78:0x02f0  */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Code duplicated, block: B:80:0x02f7 A[Catch: all -> 0x0552, TryCatch #5 {all -> 0x0552, blocks: (B:86:0x0327, B:63:0x0257, B:65:0x025b, B:67:0x0267, B:71:0x028c, B:73:0x0295, B:75:0x02a4, B:74:0x029f, B:80:0x02f7, B:81:0x0302, B:82:0x0303, B:59:0x0246), top: B:175:0x0246 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x0303 A[Catch: all -> 0x0552, TryCatch #5 {all -> 0x0552, blocks: (B:86:0x0327, B:63:0x0257, B:65:0x025b, B:67:0x0267, B:71:0x028c, B:73:0x0295, B:75:0x02a4, B:74:0x029f, B:80:0x02f7, B:81:0x0302, B:82:0x0303, B:59:0x0246), top: B:175:0x0246 }] */
    /* JADX WARN: Code duplicated, block: B:84:0x0320  */
    /* JADX WARN: Code duplicated, block: B:85:0x0322  */
    /* JADX WARN: Code duplicated, block: B:89:0x034a  */
    /* JADX WARN: Code duplicated, block: B:92:0x035a A[Catch: all -> 0x0550, TryCatch #4 {all -> 0x0550, blocks: (B:96:0x038f, B:98:0x039b, B:101:0x03a2, B:103:0x03aa, B:108:0x03b6, B:110:0x03be, B:112:0x03c4, B:114:0x03d9, B:119:0x03e5, B:90:0x0352, B:92:0x035a, B:142:0x053d, B:143:0x054f), top: B:174:0x0352 }] */
    /* JADX WARN: Code duplicated, block: B:94:0x0385  */
    /* JADX WARN: Code duplicated, block: B:95:0x0387  */
    /* JADX WARN: Code duplicated, block: B:98:0x039b A[Catch: all -> 0x0550, TryCatch #4 {all -> 0x0550, blocks: (B:96:0x038f, B:98:0x039b, B:101:0x03a2, B:103:0x03aa, B:108:0x03b6, B:110:0x03be, B:112:0x03c4, B:114:0x03d9, B:119:0x03e5, B:90:0x0352, B:92:0x035a, B:142:0x053d, B:143:0x054f), top: B:174:0x0352 }] */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x05cb, code lost:
    
        if (r0.addAudit(r16, r3) == r4) goto L163;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x021b: MOVE (r8 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:51:0x0218 */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x021a: MOVE (r2 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]), block:B:51:0x0218 */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x0218: MOVE (r10 I:??[OBJECT, ARRAY]) = (r9 I:??[OBJECT, ARRAY]), block:B:51:0x0218 */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v18, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v21 */
    /* JADX WARN: Type inference failed for: r11v22, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v25 */
    /* JADX WARN: Type inference failed for: r11v26 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r15v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r19v3 */
    /* JADX WARN: Type inference failed for: r19v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r19v5 */
    /* JADX WARN: Type inference failed for: r20v0 */
    /* JADX WARN: Type inference failed for: r20v1, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r20v4 */
    /* JADX WARN: Type inference failed for: r20v5, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r20v6 */
    /* JADX WARN: Type inference failed for: r20v7 */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v36 */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r2v38 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v41 */
    /* JADX WARN: Type inference failed for: r2v45 */
    /* JADX WARN: Type inference failed for: r2v46 */
    /* JADX WARN: Type inference failed for: r2v47 */
    /* JADX WARN: Type inference failed for: r2v48 */
    /* JADX WARN: Type inference failed for: r2v49 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v50 */
    /* JADX WARN: Type inference failed for: r2v51 */
    /* JADX WARN: Type inference failed for: r2v52 */
    /* JADX WARN: Type inference failed for: r2v53 */
    /* JADX WARN: Type inference failed for: r2v54 */
    /* JADX WARN: Type inference failed for: r2v56 */
    /* JADX WARN: Type inference failed for: r2v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r37v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync] */
    /* JADX WARN: Type inference failed for: r6v41 */
    /* JADX WARN: Type inference failed for: r6v43 */
    /* JADX WARN: Type inference failed for: r6v62 */
    /* JADX WARN: Type inference failed for: r6v63 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v38 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v41 */
    /* JADX WARN: Type inference failed for: r9v42 */
    /* JADX WARN: Type inference failed for: r9v43 */
    /* JADX WARN: Type inference failed for: r9v44, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v45 */
    /* JADX WARN: Type inference failed for: r9v48 */
    /* JADX WARN: Type inference failed for: r9v49 */
    /* JADX WARN: Type inference failed for: r9v5, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v50 */
    /* JADX WARN: Type inference failed for: r9v53 */
    /* JADX WARN: Type inference failed for: r9v55 */
    /* JADX WARN: Type inference failed for: r9v56 */
    /* JADX WARN: Type inference failed for: r9v57 */
    /* JADX WARN: Type inference failed for: r9v58 */
    /* JADX WARN: Type inference failed for: r9v59 */
    /* JADX WARN: Type inference failed for: r9v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v60 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object authenticate(Context context, String str, String str2, boolean z, Continuation<? super AuthenticatedUser> continuation) {
        C40601 c40601;
        ?? r9;
        String str3;
        Context context2;
        Context context3;
        Object obj;
        String str4;
        Object objM7781constructorimpl;
        ?? r20;
        String str5;
        ?? r2;
        Throwable thM7784exceptionOrNullimpl;
        Object obj2;
        Context context4;
        Object objFindActiveUser;
        MasrofViewModel masrofViewModel;
        int i;
        ?? r10;
        ?? r3;
        UserEntity userEntity;
        Object objSignIn;
        UserEntity userEntity2;
        Object obj3;
        AuthenticatedUser authenticatedUser;
        MasrofRepository masrofRepository;
        AuditLogEntity auditLogEntity;
        ?? r6;
        boolean z2;
        AuthenticatedUser authenticatedUser2;
        ?? r11;
        ?? r4;
        String str6;
        Object objAuthorizeCurrentDevice;
        String str7;
        Object obj4;
        MasrofViewModel masrofViewModel2;
        UserEntity userEntity3;
        FirebaseCloudSync.AccessDecision accessDecision;
        Object objCurrentProfile;
        FirebaseCloudSync.AccessDecision accessDecision2;
        Object obj5;
        MasrofViewModel masrofViewModel3;
        UserEntity userEntity4;
        String str8;
        ?? r12;
        ?? r5;
        Map map;
        FirebaseUser currentUser;
        ?? r13;
        Object obj6;
        ?? r21;
        Object obj7;
        String string;
        AppRole appRoleRoleFrom;
        FirebaseCloudSync.AccessDecision accessDecision3;
        Object obj8;
        ?? r19;
        AuthenticatedUser authenticatedUser3;
        Map map2;
        String str9;
        MasrofViewModel masrofViewModel4;
        ?? r15;
        String str10;
        ?? r7;
        UserEntity userEntity5;
        ?? r14;
        ?? r16;
        FirebaseCloudSync.AccessDecision accessDecision4;
        String string2;
        String string3;
        String email;
        String str11;
        MasrofRepository masrofRepository2;
        AuditLogEntity auditLogEntity2;
        ?? r37;
        UserEntity userEntity6;
        ?? r17;
        Context context5;
        ?? r18;
        ?? r110;
        FirebaseCloudSync firebaseCloudSync;
        MasrofRepository masrofRepository3;
        AuthenticatedUser authenticatedUser4;
        String str12;
        ?? r22;
        ?? r8;
        ?? r23;
        ?? r24;
        ?? r25 = str;
        if (continuation instanceof C40601) {
            c40601 = (C40601) continuation;
            if ((c40601.label & Integer.MIN_VALUE) != 0) {
                c40601.label -= Integer.MIN_VALUE;
            } else {
                c40601 = new C40601(continuation);
            }
        } else {
            c40601 = new C40601(continuation);
        }
        Object obj9 = c40601.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        boolean z3 = true;
        try {
            try {
                switch (c40601.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj9);
                        this.lastAuthError = null;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            MasrofViewModel masrofViewModel5 = this;
                            MasrofRepository masrofRepository4 = this.repository;
                            String string4 = StringsKt.trim((CharSequence) r25).toString();
                            context4 = context;
                            try {
                                c40601.L$0 = context4;
                                c40601.L$1 = r25;
                                str3 = str2;
                                try {
                                    c40601.L$2 = str3;
                                    c40601.L$3 = this;
                                    r9 = z;
                                    try {
                                        c40601.Z$0 = r9;
                                        c40601.I$0 = 0;
                                        c40601.label = 1;
                                        objFindActiveUser = masrofRepository4.findActiveUser(string4, c40601);
                                        if (objFindActiveUser != coroutine_suspended) {
                                            masrofViewModel = this;
                                            i = 0;
                                            r3 = r25;
                                            r10 = r9;
                                            userEntity = (UserEntity) objFindActiveUser;
                                            if (userEntity != null) {
                                                ?? r26 = masrofViewModel.cloud;
                                                c40601.L$0 = context4;
                                                c40601.L$1 = r3;
                                                c40601.L$2 = str3;
                                                c40601.L$3 = masrofViewModel;
                                                c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity);
                                                c40601.Z$0 = r10;
                                                c40601.I$0 = i;
                                                c40601.label = 3;
                                                objSignIn = r26.signIn(r3, str3, c40601);
                                                if (objSignIn == coroutine_suspended) {
                                                    userEntity2 = userEntity;
                                                    obj3 = objSignIn;
                                                    r4 = r3;
                                                    r11 = r10;
                                                    str6 = (String) obj3;
                                                    FirebaseCloudSync firebaseCloudSync2 = masrofViewModel.cloud;
                                                    c40601.L$0 = context4;
                                                    c40601.L$1 = r4;
                                                    c40601.L$2 = str3;
                                                    c40601.L$3 = masrofViewModel;
                                                    c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity2);
                                                    c40601.L$5 = str6;
                                                    c40601.Z$0 = r11;
                                                    c40601.I$0 = i;
                                                    c40601.label = 4;
                                                    objAuthorizeCurrentDevice = firebaseCloudSync2.authorizeCurrentDevice(context4, c40601);
                                                    if (objAuthorizeCurrentDevice != coroutine_suspended) {
                                                        UserEntity userEntity7 = userEntity2;
                                                        str7 = str6;
                                                        obj4 = objAuthorizeCurrentDevice;
                                                        masrofViewModel2 = masrofViewModel;
                                                        context3 = context4;
                                                        userEntity3 = userEntity7;
                                                        r25 = r4;
                                                        r9 = r11;
                                                        try {
                                                            accessDecision = (FirebaseCloudSync.AccessDecision) obj4;
                                                            if (accessDecision.getAllowed()) {
                                                                masrofViewModel2.cloud.signOut();
                                                                throw new IllegalStateException(accessDecision.getMessage().toString());
                                                            }
                                                            masrofViewModel2.isManagerSession = accessDecision.isManager();
                                                            FirebaseCloudSync firebaseCloudSync3 = masrofViewModel2.cloud;
                                                            c40601.L$0 = context3;
                                                            c40601.L$1 = r25;
                                                            c40601.L$2 = str3;
                                                            c40601.L$3 = masrofViewModel2;
                                                            c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity3);
                                                            c40601.L$5 = str7;
                                                            c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision);
                                                            c40601.Z$0 = r9;
                                                            c40601.I$0 = i;
                                                            c40601.label = 5;
                                                            objCurrentProfile = firebaseCloudSync3.currentProfile(c40601);
                                                            if (objCurrentProfile == coroutine_suspended) {
                                                                String str13 = str7;
                                                                accessDecision2 = accessDecision;
                                                                obj5 = objCurrentProfile;
                                                                masrofViewModel3 = masrofViewModel2;
                                                                userEntity4 = userEntity3;
                                                                str8 = str13;
                                                                r5 = r25;
                                                                r12 = r9;
                                                                map = (Map) obj5;
                                                                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                                                if (currentUser != null || (email = currentUser.getEmail()) == null) {
                                                                    r13 = email;
                                                                    r13 = r5;
                                                                }
                                                                r13 = email;
                                                                obj6 = map.get("fullName");
                                                                if (obj6 != null || (string3 = obj6.toString()) == null) {
                                                                    r21 = r13;
                                                                } else {
                                                                    r21 = string3;
                                                                }
                                                                obj7 = map.get("role");
                                                                if (obj7 != null) {
                                                                    string = obj7.toString();
                                                                } else {
                                                                    string = null;
                                                                }
                                                                appRoleRoleFrom = masrofViewModel3.roleFrom(string);
                                                                long jHashCode = str8.hashCode();
                                                                accessDecision3 = accessDecision2;
                                                                obj8 = map.get(HintConstants.AUTOFILL_HINT_USERNAME);
                                                                if (obj8 != null || (string2 = obj8.toString()) == null) {
                                                                    r19 = r13;
                                                                } else {
                                                                    r19 = string2;
                                                                }
                                                                authenticatedUser3 = new AuthenticatedUser(jHashCode, r19, r21, appRoleRoleFrom);
                                                                UserSession.INSTANCE.setCurrent(authenticatedUser3);
                                                                c40601.L$0 = context3;
                                                                c40601.L$1 = r5;
                                                                c40601.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                                                                c40601.L$3 = masrofViewModel3;
                                                                c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity4);
                                                                c40601.L$5 = SpillingKt.nullOutSpilledVariable(str8);
                                                                c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision3);
                                                                c40601.L$7 = SpillingKt.nullOutSpilledVariable(map);
                                                                c40601.L$8 = SpillingKt.nullOutSpilledVariable(r21);
                                                                c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                                                c40601.L$10 = SpillingKt.nullOutSpilledVariable(r13);
                                                                c40601.L$11 = authenticatedUser3;
                                                                c40601.Z$0 = r12;
                                                                c40601.I$0 = i;
                                                                c40601.label = 6;
                                                                if (masrofViewModel3.cacheLocalUser(authenticatedUser3, str3, c40601) != coroutine_suspended) {
                                                                    MasrofViewModel masrofViewModel6 = masrofViewModel3;
                                                                    map2 = map;
                                                                    str9 = str8;
                                                                    masrofViewModel4 = masrofViewModel6;
                                                                    r15 = r5;
                                                                    str10 = str3;
                                                                    r7 = r12;
                                                                    userEntity5 = userEntity4;
                                                                    r14 = r13;
                                                                    r16 = r21;
                                                                    accessDecision4 = accessDecision3;
                                                                    str11 = str9;
                                                                    if (r7 != 0) {
                                                                        RememberedLogin.INSTANCE.save(context3, authenticatedUser3.getUsername());
                                                                    } else {
                                                                        RememberedLogin.INSTANCE.clear(context3);
                                                                    }
                                                                    masrofRepository2 = masrofViewModel4.repository;
                                                                    auditLogEntity2 = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "LOGIN_SUCCESS", "تسجيل دخول Firebase ناجح", 0L, 33, null);
                                                                    r37 = r14;
                                                                    c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                                                    c40601.L$1 = r15;
                                                                    c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                                                                    c40601.L$3 = masrofViewModel4;
                                                                    c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity5);
                                                                    c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                                                    c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                                                                    c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                                                                    c40601.L$8 = SpillingKt.nullOutSpilledVariable(r16);
                                                                    c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                                                    c40601.L$10 = SpillingKt.nullOutSpilledVariable(r37);
                                                                    c40601.L$11 = authenticatedUser3;
                                                                    c40601.Z$0 = r7;
                                                                    c40601.I$0 = i;
                                                                    c40601.label = 7;
                                                                    if (masrofRepository2.addAudit(auditLogEntity2, c40601) == coroutine_suspended) {
                                                                        userEntity6 = userEntity5;
                                                                        r17 = r37;
                                                                        r25 = r7;
                                                                        r110 = r16;
                                                                        r18 = r15;
                                                                        firebaseCloudSync = masrofViewModel4.cloud;
                                                                        UserEntity userEntity8 = userEntity6;
                                                                        masrofRepository3 = masrofViewModel4.repository;
                                                                        c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                                                        c40601.L$1 = r18;
                                                                        c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                                                                        c40601.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                                                        c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity8);
                                                                        c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                                                        c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                                                                        c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                                                                        c40601.L$8 = SpillingKt.nullOutSpilledVariable(r110);
                                                                        c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                                                        c40601.L$10 = SpillingKt.nullOutSpilledVariable(r17);
                                                                        c40601.L$11 = authenticatedUser3;
                                                                        c40601.Z$0 = r25;
                                                                        c40601.I$0 = i;
                                                                        c40601.label = 8;
                                                                        if (firebaseCloudSync.syncDocuments(masrofRepository3, c40601) != coroutine_suspended) {
                                                                            authenticatedUser4 = authenticatedUser3;
                                                                            str12 = str10;
                                                                            r22 = r18;
                                                                            r24 = r25;
                                                                            authenticatedUser2 = authenticatedUser4;
                                                                            str5 = str12;
                                                                            r8 = r22;
                                                                            r23 = r24;
                                                                            objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
                                                                            r20 = r8;
                                                                            r2 = r23;
                                                                            thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                                                            if (thM7784exceptionOrNullimpl == null) {
                                                                                return objM7781constructorimpl;
                                                                            }
                                                                            this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                                                            MasrofRepository masrofRepository5 = this.repository;
                                                                            AuditLogEntity auditLogEntity3 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                                                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                                                            c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                                                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                                                            c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                                                            obj2 = null;
                                                                            c40601.L$4 = null;
                                                                            c40601.L$5 = null;
                                                                            c40601.L$6 = null;
                                                                            c40601.L$7 = null;
                                                                            c40601.L$8 = null;
                                                                            c40601.L$9 = null;
                                                                            c40601.L$10 = null;
                                                                            c40601.L$11 = null;
                                                                            c40601.Z$0 = r2;
                                                                            c40601.I$0 = 0;
                                                                            c40601.label = 9;
                                                                        }
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                        } catch (Throwable th) {
                                                            th = th;
                                                            Result.Companion companion2 = Result.INSTANCE;
                                                            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                                                            r20 = r25;
                                                            str5 = str3;
                                                            r2 = r9;
                                                            thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                                            if (thM7784exceptionOrNullimpl == null) {
                                                                return objM7781constructorimpl;
                                                            }
                                                            this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                                            MasrofRepository masrofRepository6 = this.repository;
                                                            AuditLogEntity auditLogEntity4 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                                            c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                                            c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                                            obj2 = null;
                                                            c40601.L$4 = null;
                                                            c40601.L$5 = null;
                                                            c40601.L$6 = null;
                                                            c40601.L$7 = null;
                                                            c40601.L$8 = null;
                                                            c40601.L$9 = null;
                                                            c40601.L$10 = null;
                                                            c40601.L$11 = null;
                                                            c40601.Z$0 = r2;
                                                            c40601.I$0 = 0;
                                                            c40601.label = 9;
                                                            break;
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (AuthSecurity.INSTANCE.verify(str3, userEntity.getPasswordHash())) {
                                                    throw new IllegalStateException("بيانات الدخول المحلية غير صحيحة".toString());
                                                }
                                                authenticatedUser = new AuthenticatedUser(userEntity.getId(), userEntity.getUsername(), userEntity.getFullName(), masrofViewModel.roleFrom(userEntity.getRole()));
                                                if (authenticatedUser.getRole() == AppRole.ADMIN) {
                                                    z3 = false;
                                                }
                                                masrofViewModel.isManagerSession = z3;
                                                UserSession.INSTANCE.setCurrent(authenticatedUser);
                                                if (r10 != 0) {
                                                    RememberedLogin.INSTANCE.save(context4, authenticatedUser.getUsername());
                                                } else {
                                                    RememberedLogin.INSTANCE.clear(context4);
                                                }
                                                masrofRepository = masrofViewModel.repository;
                                                auditLogEntity = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser.getId()), authenticatedUser.getUsername(), "LOGIN_LOCAL_SUCCESS", "دخول محلي دون إنترنت", 0L, 33, null);
                                                c40601.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                                c40601.L$1 = r3;
                                                c40601.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                                                c40601.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel);
                                                c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity);
                                                c40601.L$5 = authenticatedUser;
                                                c40601.Z$0 = r10;
                                                c40601.I$0 = i;
                                                c40601.label = 2;
                                                if (masrofRepository.addAudit(auditLogEntity, c40601) == coroutine_suspended) {
                                                    r6 = r3;
                                                    str5 = str3;
                                                    z2 = r10 == true ? 1 : 0;
                                                    authenticatedUser2 = authenticatedUser;
                                                    context3 = context4;
                                                    r23 = z2;
                                                    r8 = r6;
                                                    try {
                                                        objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
                                                        r20 = r8;
                                                        r2 = r23;
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        r9 = r23 == true ? 1 : 0;
                                                        str3 = str5;
                                                        r25 = r8;
                                                        Result.Companion companion3 = Result.INSTANCE;
                                                        objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                                                        r20 = r25;
                                                        str5 = str3;
                                                        r2 = r9;
                                                    }
                                                    thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                                    if (thM7784exceptionOrNullimpl == null) {
                                                        return objM7781constructorimpl;
                                                    }
                                                    this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                                    MasrofRepository masrofRepository7 = this.repository;
                                                    AuditLogEntity auditLogEntity5 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                                    c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                                    c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                                    c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                                    c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                                    obj2 = null;
                                                    c40601.L$4 = null;
                                                    c40601.L$5 = null;
                                                    c40601.L$6 = null;
                                                    c40601.L$7 = null;
                                                    c40601.L$8 = null;
                                                    c40601.L$9 = null;
                                                    c40601.L$10 = null;
                                                    c40601.L$11 = null;
                                                    c40601.Z$0 = r2;
                                                    c40601.I$0 = 0;
                                                    c40601.label = 9;
                                                    break;
                                                }
                                            }
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        context3 = context4;
                                        Result.Companion companion4 = Result.INSTANCE;
                                        objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                                        r20 = r25;
                                        str5 = str3;
                                        r2 = r9;
                                        thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                        if (thM7784exceptionOrNullimpl == null) {
                                            return objM7781constructorimpl;
                                        }
                                        this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                        MasrofRepository masrofRepository8 = this.repository;
                                        AuditLogEntity auditLogEntity6 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                        c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                        c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                        c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                        c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                        obj2 = null;
                                        c40601.L$4 = null;
                                        c40601.L$5 = null;
                                        c40601.L$6 = null;
                                        c40601.L$7 = null;
                                        c40601.L$8 = null;
                                        c40601.L$9 = null;
                                        c40601.L$10 = null;
                                        c40601.L$11 = null;
                                        c40601.Z$0 = r2;
                                        c40601.I$0 = 0;
                                        c40601.label = 9;
                                        break;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    r9 = z;
                                    context3 = context4;
                                    Result.Companion companion5 = Result.INSTANCE;
                                    objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                                    r20 = r25;
                                    str5 = str3;
                                    r2 = r9;
                                    thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                    if (thM7784exceptionOrNullimpl == null) {
                                        return objM7781constructorimpl;
                                    }
                                    this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                    MasrofRepository masrofRepository9 = this.repository;
                                    AuditLogEntity auditLogEntity7 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                    c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                    c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                    c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                    c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                    obj2 = null;
                                    c40601.L$4 = null;
                                    c40601.L$5 = null;
                                    c40601.L$6 = null;
                                    c40601.L$7 = null;
                                    c40601.L$8 = null;
                                    c40601.L$9 = null;
                                    c40601.L$10 = null;
                                    c40601.L$11 = null;
                                    c40601.Z$0 = r2;
                                    c40601.I$0 = 0;
                                    c40601.label = 9;
                                    break;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                str3 = str2;
                                r9 = z;
                                context3 = context4;
                                Result.Companion companion6 = Result.INSTANCE;
                                objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                                r20 = r25;
                                str5 = str3;
                                r2 = r9;
                                thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                if (thM7784exceptionOrNullimpl == null) {
                                    return objM7781constructorimpl;
                                }
                                this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                MasrofRepository masrofRepository10 = this.repository;
                                AuditLogEntity auditLogEntity8 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                obj2 = null;
                                c40601.L$4 = null;
                                c40601.L$5 = null;
                                c40601.L$6 = null;
                                c40601.L$7 = null;
                                c40601.L$8 = null;
                                c40601.L$9 = null;
                                c40601.L$10 = null;
                                c40601.L$11 = null;
                                c40601.Z$0 = r2;
                                c40601.I$0 = 0;
                                c40601.label = 9;
                                break;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            context4 = context;
                        }
                        return coroutine_suspended;
                    case 1:
                        int i2 = c40601.I$0;
                        boolean z4 = c40601.Z$0;
                        MasrofViewModel masrofViewModel7 = (MasrofViewModel) c40601.L$3;
                        String str14 = (String) c40601.L$2;
                        String str15 = (String) c40601.L$1;
                        Context context6 = (Context) c40601.L$0;
                        ResultKt.throwOnFailure(obj9);
                        r10 = z4;
                        r3 = str15;
                        str3 = str14;
                        context4 = context6;
                        masrofViewModel = masrofViewModel7;
                        i = i2;
                        objFindActiveUser = obj9;
                        userEntity = (UserEntity) objFindActiveUser;
                        if (userEntity != null) {
                            ?? r27 = masrofViewModel.cloud;
                            c40601.L$0 = context4;
                            c40601.L$1 = r3;
                            c40601.L$2 = str3;
                            c40601.L$3 = masrofViewModel;
                            c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity);
                            c40601.Z$0 = r10;
                            c40601.I$0 = i;
                            c40601.label = 3;
                            objSignIn = r27.signIn(r3, str3, c40601);
                            if (objSignIn == coroutine_suspended) {
                                userEntity2 = userEntity;
                                obj3 = objSignIn;
                                r4 = r3;
                                r11 = r10;
                                str6 = (String) obj3;
                                FirebaseCloudSync firebaseCloudSync4 = masrofViewModel.cloud;
                                c40601.L$0 = context4;
                                c40601.L$1 = r4;
                                c40601.L$2 = str3;
                                c40601.L$3 = masrofViewModel;
                                c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity2);
                                c40601.L$5 = str6;
                                c40601.Z$0 = r11;
                                c40601.I$0 = i;
                                c40601.label = 4;
                                objAuthorizeCurrentDevice = firebaseCloudSync4.authorizeCurrentDevice(context4, c40601);
                                if (objAuthorizeCurrentDevice != coroutine_suspended) {
                                    UserEntity userEntity9 = userEntity2;
                                    str7 = str6;
                                    obj4 = objAuthorizeCurrentDevice;
                                    masrofViewModel2 = masrofViewModel;
                                    context3 = context4;
                                    userEntity3 = userEntity9;
                                    r25 = r4;
                                    r9 = r11;
                                    accessDecision = (FirebaseCloudSync.AccessDecision) obj4;
                                    if (accessDecision.getAllowed()) {
                                        masrofViewModel2.cloud.signOut();
                                        throw new IllegalStateException(accessDecision.getMessage().toString());
                                    }
                                    masrofViewModel2.isManagerSession = accessDecision.isManager();
                                    FirebaseCloudSync firebaseCloudSync5 = masrofViewModel2.cloud;
                                    c40601.L$0 = context3;
                                    c40601.L$1 = r25;
                                    c40601.L$2 = str3;
                                    c40601.L$3 = masrofViewModel2;
                                    c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity3);
                                    c40601.L$5 = str7;
                                    c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision);
                                    c40601.Z$0 = r9;
                                    c40601.I$0 = i;
                                    c40601.label = 5;
                                    objCurrentProfile = firebaseCloudSync5.currentProfile(c40601);
                                    if (objCurrentProfile == coroutine_suspended) {
                                        String str16 = str7;
                                        accessDecision2 = accessDecision;
                                        obj5 = objCurrentProfile;
                                        masrofViewModel3 = masrofViewModel2;
                                        userEntity4 = userEntity3;
                                        str8 = str16;
                                        r5 = r25;
                                        r12 = r9;
                                        map = (Map) obj5;
                                        currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                        if (currentUser != null) {
                                            r13 = email;
                                            r13 = r5;
                                        } else {
                                            r13 = email;
                                            r13 = r5;
                                        }
                                        r13 = email;
                                        obj6 = map.get("fullName");
                                        if (obj6 != null) {
                                            r21 = r13;
                                        } else {
                                            r21 = r13;
                                        }
                                        obj7 = map.get("role");
                                        if (obj7 != null) {
                                            string = obj7.toString();
                                        } else {
                                            string = null;
                                        }
                                        appRoleRoleFrom = masrofViewModel3.roleFrom(string);
                                        long jHashCode2 = str8.hashCode();
                                        accessDecision3 = accessDecision2;
                                        obj8 = map.get(HintConstants.AUTOFILL_HINT_USERNAME);
                                        if (obj8 != null) {
                                            r19 = r13;
                                        } else {
                                            r19 = r13;
                                        }
                                        authenticatedUser3 = new AuthenticatedUser(jHashCode2, r19, r21, appRoleRoleFrom);
                                        UserSession.INSTANCE.setCurrent(authenticatedUser3);
                                        c40601.L$0 = context3;
                                        c40601.L$1 = r5;
                                        c40601.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                                        c40601.L$3 = masrofViewModel3;
                                        c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity4);
                                        c40601.L$5 = SpillingKt.nullOutSpilledVariable(str8);
                                        c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision3);
                                        c40601.L$7 = SpillingKt.nullOutSpilledVariable(map);
                                        c40601.L$8 = SpillingKt.nullOutSpilledVariable(r21);
                                        c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                        c40601.L$10 = SpillingKt.nullOutSpilledVariable(r13);
                                        c40601.L$11 = authenticatedUser3;
                                        c40601.Z$0 = r12;
                                        c40601.I$0 = i;
                                        c40601.label = 6;
                                        if (masrofViewModel3.cacheLocalUser(authenticatedUser3, str3, c40601) != coroutine_suspended) {
                                            MasrofViewModel masrofViewModel8 = masrofViewModel3;
                                            map2 = map;
                                            str9 = str8;
                                            masrofViewModel4 = masrofViewModel8;
                                            r15 = r5;
                                            str10 = str3;
                                            r7 = r12;
                                            userEntity5 = userEntity4;
                                            r14 = r13;
                                            r16 = r21;
                                            accessDecision4 = accessDecision3;
                                            str11 = str9;
                                            if (r7 != 0) {
                                                RememberedLogin.INSTANCE.save(context3, authenticatedUser3.getUsername());
                                            } else {
                                                RememberedLogin.INSTANCE.clear(context3);
                                            }
                                            masrofRepository2 = masrofViewModel4.repository;
                                            auditLogEntity2 = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "LOGIN_SUCCESS", "تسجيل دخول Firebase ناجح", 0L, 33, null);
                                            r37 = r14;
                                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                            c40601.L$1 = r15;
                                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                                            c40601.L$3 = masrofViewModel4;
                                            c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity5);
                                            c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                            c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                                            c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                                            c40601.L$8 = SpillingKt.nullOutSpilledVariable(r16);
                                            c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                            c40601.L$10 = SpillingKt.nullOutSpilledVariable(r37);
                                            c40601.L$11 = authenticatedUser3;
                                            c40601.Z$0 = r7;
                                            c40601.I$0 = i;
                                            c40601.label = 7;
                                            if (masrofRepository2.addAudit(auditLogEntity2, c40601) == coroutine_suspended) {
                                                userEntity6 = userEntity5;
                                                r17 = r37;
                                                r25 = r7;
                                                r110 = r16;
                                                r18 = r15;
                                                firebaseCloudSync = masrofViewModel4.cloud;
                                                UserEntity userEntity10 = userEntity6;
                                                masrofRepository3 = masrofViewModel4.repository;
                                                c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                                c40601.L$1 = r18;
                                                c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                                                c40601.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                                c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity10);
                                                c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                                c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                                                c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                                                c40601.L$8 = SpillingKt.nullOutSpilledVariable(r110);
                                                c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                                c40601.L$10 = SpillingKt.nullOutSpilledVariable(r17);
                                                c40601.L$11 = authenticatedUser3;
                                                c40601.Z$0 = r25;
                                                c40601.I$0 = i;
                                                c40601.label = 8;
                                                if (firebaseCloudSync.syncDocuments(masrofRepository3, c40601) != coroutine_suspended) {
                                                    authenticatedUser4 = authenticatedUser3;
                                                    str12 = str10;
                                                    r22 = r18;
                                                    r24 = r25;
                                                    authenticatedUser2 = authenticatedUser4;
                                                    str5 = str12;
                                                    r8 = r22;
                                                    r23 = r24;
                                                    objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
                                                    r20 = r8;
                                                    r2 = r23;
                                                    thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                                    if (thM7784exceptionOrNullimpl == null) {
                                                        return objM7781constructorimpl;
                                                    }
                                                    this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                                    MasrofRepository masrofRepository11 = this.repository;
                                                    AuditLogEntity auditLogEntity9 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                                    c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                                    c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                                    c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                                    c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                                    obj2 = null;
                                                    c40601.L$4 = null;
                                                    c40601.L$5 = null;
                                                    c40601.L$6 = null;
                                                    c40601.L$7 = null;
                                                    c40601.L$8 = null;
                                                    c40601.L$9 = null;
                                                    c40601.L$10 = null;
                                                    c40601.L$11 = null;
                                                    c40601.Z$0 = r2;
                                                    c40601.I$0 = 0;
                                                    c40601.label = 9;
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (AuthSecurity.INSTANCE.verify(str3, userEntity.getPasswordHash())) {
                                throw new IllegalStateException("بيانات الدخول المحلية غير صحيحة".toString());
                            }
                            authenticatedUser = new AuthenticatedUser(userEntity.getId(), userEntity.getUsername(), userEntity.getFullName(), masrofViewModel.roleFrom(userEntity.getRole()));
                            if (authenticatedUser.getRole() == AppRole.ADMIN) {
                                z3 = false;
                            }
                            masrofViewModel.isManagerSession = z3;
                            UserSession.INSTANCE.setCurrent(authenticatedUser);
                            if (r10 != 0) {
                                RememberedLogin.INSTANCE.save(context4, authenticatedUser.getUsername());
                            } else {
                                RememberedLogin.INSTANCE.clear(context4);
                            }
                            masrofRepository = masrofViewModel.repository;
                            auditLogEntity = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser.getId()), authenticatedUser.getUsername(), "LOGIN_LOCAL_SUCCESS", "دخول محلي دون إنترنت", 0L, 33, null);
                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                            c40601.L$1 = r3;
                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                            c40601.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel);
                            c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity);
                            c40601.L$5 = authenticatedUser;
                            c40601.Z$0 = r10;
                            c40601.I$0 = i;
                            c40601.label = 2;
                            if (masrofRepository.addAudit(auditLogEntity, c40601) == coroutine_suspended) {
                                r6 = r3;
                                str5 = str3;
                                z2 = r10 == true ? 1 : 0;
                                authenticatedUser2 = authenticatedUser;
                                context3 = context4;
                                r23 = z2;
                                r8 = r6;
                                objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
                                r20 = r8;
                                r2 = r23;
                                thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                if (thM7784exceptionOrNullimpl == null) {
                                    return objM7781constructorimpl;
                                }
                                this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                MasrofRepository masrofRepository12 = this.repository;
                                AuditLogEntity auditLogEntity10 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                obj2 = null;
                                c40601.L$4 = null;
                                c40601.L$5 = null;
                                c40601.L$6 = null;
                                c40601.L$7 = null;
                                c40601.L$8 = null;
                                c40601.L$9 = null;
                                c40601.L$10 = null;
                                c40601.L$11 = null;
                                c40601.Z$0 = r2;
                                c40601.I$0 = 0;
                                c40601.label = 9;
                                break;
                            }
                        }
                        return coroutine_suspended;
                    case 2:
                        int i3 = c40601.I$0;
                        boolean z5 = c40601.Z$0;
                        authenticatedUser2 = (AuthenticatedUser) c40601.L$5;
                        str5 = (String) c40601.L$2;
                        String str17 = (String) c40601.L$1;
                        context4 = (Context) c40601.L$0;
                        try {
                            ResultKt.throwOnFailure(obj9);
                            z2 = z5;
                            r6 = str17;
                            context3 = context4;
                            r23 = z2;
                            r8 = r6;
                            objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
                            r20 = r8;
                            r2 = r23;
                        } catch (Throwable th7) {
                            th = th7;
                            r9 = z5 ? 1 : 0;
                            str3 = str5;
                            r25 = str17;
                            context3 = context4;
                            Result.Companion companion7 = Result.INSTANCE;
                            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                            r20 = r25;
                            str5 = str3;
                            r2 = r9;
                            thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                            if (thM7784exceptionOrNullimpl == null) {
                                return objM7781constructorimpl;
                            }
                            this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                            MasrofRepository masrofRepository13 = this.repository;
                            AuditLogEntity auditLogEntity11 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                            c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                            c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                            obj2 = null;
                            c40601.L$4 = null;
                            c40601.L$5 = null;
                            c40601.L$6 = null;
                            c40601.L$7 = null;
                            c40601.L$8 = null;
                            c40601.L$9 = null;
                            c40601.L$10 = null;
                            c40601.L$11 = null;
                            c40601.Z$0 = r2;
                            c40601.I$0 = 0;
                            c40601.label = 9;
                        }
                        thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                        if (thM7784exceptionOrNullimpl == null) {
                            return objM7781constructorimpl;
                        }
                        this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                        MasrofRepository masrofRepository14 = this.repository;
                        AuditLogEntity auditLogEntity12 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                        c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                        c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                        c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                        c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                        obj2 = null;
                        c40601.L$4 = null;
                        c40601.L$5 = null;
                        c40601.L$6 = null;
                        c40601.L$7 = null;
                        c40601.L$8 = null;
                        c40601.L$9 = null;
                        c40601.L$10 = null;
                        c40601.L$11 = null;
                        c40601.Z$0 = r2;
                        c40601.I$0 = 0;
                        c40601.label = 9;
                        break;
                    case 3:
                        int i4 = c40601.I$0;
                        boolean z6 = c40601.Z$0;
                        UserEntity userEntity11 = (UserEntity) c40601.L$4;
                        MasrofViewModel masrofViewModel9 = (MasrofViewModel) c40601.L$3;
                        String str18 = (String) c40601.L$2;
                        String str19 = (String) c40601.L$1;
                        Context context7 = (Context) c40601.L$0;
                        ResultKt.throwOnFailure(obj9);
                        r11 = z6;
                        r4 = str19;
                        str3 = str18;
                        context4 = context7;
                        masrofViewModel = masrofViewModel9;
                        userEntity2 = userEntity11;
                        i = i4;
                        obj3 = obj9;
                        str6 = (String) obj3;
                        FirebaseCloudSync firebaseCloudSync6 = masrofViewModel.cloud;
                        c40601.L$0 = context4;
                        c40601.L$1 = r4;
                        c40601.L$2 = str3;
                        c40601.L$3 = masrofViewModel;
                        c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity2);
                        c40601.L$5 = str6;
                        c40601.Z$0 = r11;
                        c40601.I$0 = i;
                        c40601.label = 4;
                        objAuthorizeCurrentDevice = firebaseCloudSync6.authorizeCurrentDevice(context4, c40601);
                        if (objAuthorizeCurrentDevice != coroutine_suspended) {
                            UserEntity userEntity12 = userEntity2;
                            str7 = str6;
                            obj4 = objAuthorizeCurrentDevice;
                            masrofViewModel2 = masrofViewModel;
                            context3 = context4;
                            userEntity3 = userEntity12;
                            r25 = r4;
                            r9 = r11;
                            accessDecision = (FirebaseCloudSync.AccessDecision) obj4;
                            if (accessDecision.getAllowed()) {
                                masrofViewModel2.cloud.signOut();
                                throw new IllegalStateException(accessDecision.getMessage().toString());
                            }
                            masrofViewModel2.isManagerSession = accessDecision.isManager();
                            FirebaseCloudSync firebaseCloudSync7 = masrofViewModel2.cloud;
                            c40601.L$0 = context3;
                            c40601.L$1 = r25;
                            c40601.L$2 = str3;
                            c40601.L$3 = masrofViewModel2;
                            c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity3);
                            c40601.L$5 = str7;
                            c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision);
                            c40601.Z$0 = r9;
                            c40601.I$0 = i;
                            c40601.label = 5;
                            objCurrentProfile = firebaseCloudSync7.currentProfile(c40601);
                            if (objCurrentProfile == coroutine_suspended) {
                                String str110 = str7;
                                accessDecision2 = accessDecision;
                                obj5 = objCurrentProfile;
                                masrofViewModel3 = masrofViewModel2;
                                userEntity4 = userEntity3;
                                str8 = str110;
                                r5 = r25;
                                r12 = r9;
                                map = (Map) obj5;
                                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (currentUser != null) {
                                    r13 = email;
                                    r13 = r5;
                                } else {
                                    r13 = email;
                                    r13 = r5;
                                }
                                r13 = email;
                                obj6 = map.get("fullName");
                                if (obj6 != null) {
                                    r21 = r13;
                                } else {
                                    r21 = r13;
                                }
                                obj7 = map.get("role");
                                if (obj7 != null) {
                                    string = obj7.toString();
                                } else {
                                    string = null;
                                }
                                appRoleRoleFrom = masrofViewModel3.roleFrom(string);
                                long jHashCode3 = str8.hashCode();
                                accessDecision3 = accessDecision2;
                                obj8 = map.get(HintConstants.AUTOFILL_HINT_USERNAME);
                                if (obj8 != null) {
                                    r19 = r13;
                                } else {
                                    r19 = r13;
                                }
                                authenticatedUser3 = new AuthenticatedUser(jHashCode3, r19, r21, appRoleRoleFrom);
                                UserSession.INSTANCE.setCurrent(authenticatedUser3);
                                c40601.L$0 = context3;
                                c40601.L$1 = r5;
                                c40601.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                                c40601.L$3 = masrofViewModel3;
                                c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity4);
                                c40601.L$5 = SpillingKt.nullOutSpilledVariable(str8);
                                c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision3);
                                c40601.L$7 = SpillingKt.nullOutSpilledVariable(map);
                                c40601.L$8 = SpillingKt.nullOutSpilledVariable(r21);
                                c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                c40601.L$10 = SpillingKt.nullOutSpilledVariable(r13);
                                c40601.L$11 = authenticatedUser3;
                                c40601.Z$0 = r12;
                                c40601.I$0 = i;
                                c40601.label = 6;
                                if (masrofViewModel3.cacheLocalUser(authenticatedUser3, str3, c40601) != coroutine_suspended) {
                                    MasrofViewModel masrofViewModel10 = masrofViewModel3;
                                    map2 = map;
                                    str9 = str8;
                                    masrofViewModel4 = masrofViewModel10;
                                    r15 = r5;
                                    str10 = str3;
                                    r7 = r12;
                                    userEntity5 = userEntity4;
                                    r14 = r13;
                                    r16 = r21;
                                    accessDecision4 = accessDecision3;
                                    str11 = str9;
                                    if (r7 != 0) {
                                        RememberedLogin.INSTANCE.save(context3, authenticatedUser3.getUsername());
                                    } else {
                                        RememberedLogin.INSTANCE.clear(context3);
                                    }
                                    masrofRepository2 = masrofViewModel4.repository;
                                    auditLogEntity2 = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "LOGIN_SUCCESS", "تسجيل دخول Firebase ناجح", 0L, 33, null);
                                    r37 = r14;
                                    c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                    c40601.L$1 = r15;
                                    c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                                    c40601.L$3 = masrofViewModel4;
                                    c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity5);
                                    c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                    c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                                    c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                                    c40601.L$8 = SpillingKt.nullOutSpilledVariable(r16);
                                    c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                    c40601.L$10 = SpillingKt.nullOutSpilledVariable(r37);
                                    c40601.L$11 = authenticatedUser3;
                                    c40601.Z$0 = r7;
                                    c40601.I$0 = i;
                                    c40601.label = 7;
                                    if (masrofRepository2.addAudit(auditLogEntity2, c40601) == coroutine_suspended) {
                                        userEntity6 = userEntity5;
                                        r17 = r37;
                                        r25 = r7;
                                        r110 = r16;
                                        r18 = r15;
                                        firebaseCloudSync = masrofViewModel4.cloud;
                                        UserEntity userEntity13 = userEntity6;
                                        masrofRepository3 = masrofViewModel4.repository;
                                        c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                        c40601.L$1 = r18;
                                        c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                                        c40601.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                        c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity13);
                                        c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                        c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                                        c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                                        c40601.L$8 = SpillingKt.nullOutSpilledVariable(r110);
                                        c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                        c40601.L$10 = SpillingKt.nullOutSpilledVariable(r17);
                                        c40601.L$11 = authenticatedUser3;
                                        c40601.Z$0 = r25;
                                        c40601.I$0 = i;
                                        c40601.label = 8;
                                        if (firebaseCloudSync.syncDocuments(masrofRepository3, c40601) != coroutine_suspended) {
                                            authenticatedUser4 = authenticatedUser3;
                                            str12 = str10;
                                            r22 = r18;
                                            r24 = r25;
                                            authenticatedUser2 = authenticatedUser4;
                                            str5 = str12;
                                            r8 = r22;
                                            r23 = r24;
                                            objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
                                            r20 = r8;
                                            r2 = r23;
                                            thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                            if (thM7784exceptionOrNullimpl == null) {
                                                return objM7781constructorimpl;
                                            }
                                            this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                            MasrofRepository masrofRepository15 = this.repository;
                                            AuditLogEntity auditLogEntity13 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                            c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                            c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                            obj2 = null;
                                            c40601.L$4 = null;
                                            c40601.L$5 = null;
                                            c40601.L$6 = null;
                                            c40601.L$7 = null;
                                            c40601.L$8 = null;
                                            c40601.L$9 = null;
                                            c40601.L$10 = null;
                                            c40601.L$11 = null;
                                            c40601.Z$0 = r2;
                                            c40601.I$0 = 0;
                                            c40601.label = 9;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        return coroutine_suspended;
                    case 4:
                        int i5 = c40601.I$0;
                        boolean z7 = c40601.Z$0;
                        String str20 = (String) c40601.L$5;
                        UserEntity userEntity14 = (UserEntity) c40601.L$4;
                        MasrofViewModel masrofViewModel11 = (MasrofViewModel) c40601.L$3;
                        str3 = (String) c40601.L$2;
                        String str21 = (String) c40601.L$1;
                        context3 = (Context) c40601.L$0;
                        try {
                            ResultKt.throwOnFailure(obj9);
                            r9 = z7;
                            r25 = str21;
                            masrofViewModel2 = masrofViewModel11;
                            userEntity3 = userEntity14;
                            str7 = str20;
                            i = i5;
                            obj4 = obj9;
                            accessDecision = (FirebaseCloudSync.AccessDecision) obj4;
                            if (accessDecision.getAllowed()) {
                                masrofViewModel2.cloud.signOut();
                                throw new IllegalStateException(accessDecision.getMessage().toString());
                            }
                            masrofViewModel2.isManagerSession = accessDecision.isManager();
                            FirebaseCloudSync firebaseCloudSync8 = masrofViewModel2.cloud;
                            c40601.L$0 = context3;
                            c40601.L$1 = r25;
                            c40601.L$2 = str3;
                            c40601.L$3 = masrofViewModel2;
                            c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity3);
                            c40601.L$5 = str7;
                            c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision);
                            c40601.Z$0 = r9;
                            c40601.I$0 = i;
                            c40601.label = 5;
                            objCurrentProfile = firebaseCloudSync8.currentProfile(c40601);
                            if (objCurrentProfile == coroutine_suspended) {
                                String str111 = str7;
                                accessDecision2 = accessDecision;
                                obj5 = objCurrentProfile;
                                masrofViewModel3 = masrofViewModel2;
                                userEntity4 = userEntity3;
                                str8 = str111;
                                r5 = r25;
                                r12 = r9;
                                map = (Map) obj5;
                                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (currentUser != null) {
                                    r13 = email;
                                    r13 = r5;
                                } else {
                                    r13 = email;
                                    r13 = r5;
                                }
                                r13 = email;
                                obj6 = map.get("fullName");
                                if (obj6 != null) {
                                    r21 = r13;
                                } else {
                                    r21 = r13;
                                }
                                obj7 = map.get("role");
                                if (obj7 != null) {
                                    string = obj7.toString();
                                } else {
                                    string = null;
                                }
                                appRoleRoleFrom = masrofViewModel3.roleFrom(string);
                                long jHashCode4 = str8.hashCode();
                                accessDecision3 = accessDecision2;
                                obj8 = map.get(HintConstants.AUTOFILL_HINT_USERNAME);
                                if (obj8 != null) {
                                    r19 = r13;
                                } else {
                                    r19 = r13;
                                }
                                authenticatedUser3 = new AuthenticatedUser(jHashCode4, r19, r21, appRoleRoleFrom);
                                UserSession.INSTANCE.setCurrent(authenticatedUser3);
                                c40601.L$0 = context3;
                                c40601.L$1 = r5;
                                c40601.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                                c40601.L$3 = masrofViewModel3;
                                c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity4);
                                c40601.L$5 = SpillingKt.nullOutSpilledVariable(str8);
                                c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision3);
                                c40601.L$7 = SpillingKt.nullOutSpilledVariable(map);
                                c40601.L$8 = SpillingKt.nullOutSpilledVariable(r21);
                                c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                c40601.L$10 = SpillingKt.nullOutSpilledVariable(r13);
                                c40601.L$11 = authenticatedUser3;
                                c40601.Z$0 = r12;
                                c40601.I$0 = i;
                                c40601.label = 6;
                                if (masrofViewModel3.cacheLocalUser(authenticatedUser3, str3, c40601) != coroutine_suspended) {
                                    MasrofViewModel masrofViewModel12 = masrofViewModel3;
                                    map2 = map;
                                    str9 = str8;
                                    masrofViewModel4 = masrofViewModel12;
                                    r15 = r5;
                                    str10 = str3;
                                    r7 = r12;
                                    userEntity5 = userEntity4;
                                    r14 = r13;
                                    r16 = r21;
                                    accessDecision4 = accessDecision3;
                                    str11 = str9;
                                    if (r7 != 0) {
                                        RememberedLogin.INSTANCE.save(context3, authenticatedUser3.getUsername());
                                    } else {
                                        RememberedLogin.INSTANCE.clear(context3);
                                    }
                                    masrofRepository2 = masrofViewModel4.repository;
                                    auditLogEntity2 = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "LOGIN_SUCCESS", "تسجيل دخول Firebase ناجح", 0L, 33, null);
                                    r37 = r14;
                                    c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                    c40601.L$1 = r15;
                                    c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                                    c40601.L$3 = masrofViewModel4;
                                    c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity5);
                                    c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                    c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                                    c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                                    c40601.L$8 = SpillingKt.nullOutSpilledVariable(r16);
                                    c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                    c40601.L$10 = SpillingKt.nullOutSpilledVariable(r37);
                                    c40601.L$11 = authenticatedUser3;
                                    c40601.Z$0 = r7;
                                    c40601.I$0 = i;
                                    c40601.label = 7;
                                    if (masrofRepository2.addAudit(auditLogEntity2, c40601) == coroutine_suspended) {
                                        userEntity6 = userEntity5;
                                        r17 = r37;
                                        r25 = r7;
                                        r110 = r16;
                                        r18 = r15;
                                        firebaseCloudSync = masrofViewModel4.cloud;
                                        UserEntity userEntity15 = userEntity6;
                                        masrofRepository3 = masrofViewModel4.repository;
                                        c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                        c40601.L$1 = r18;
                                        c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                                        c40601.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                        c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity15);
                                        c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                        c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                                        c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                                        c40601.L$8 = SpillingKt.nullOutSpilledVariable(r110);
                                        c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                        c40601.L$10 = SpillingKt.nullOutSpilledVariable(r17);
                                        c40601.L$11 = authenticatedUser3;
                                        c40601.Z$0 = r25;
                                        c40601.I$0 = i;
                                        c40601.label = 8;
                                        if (firebaseCloudSync.syncDocuments(masrofRepository3, c40601) != coroutine_suspended) {
                                            authenticatedUser4 = authenticatedUser3;
                                            str12 = str10;
                                            r22 = r18;
                                            r24 = r25;
                                            authenticatedUser2 = authenticatedUser4;
                                            str5 = str12;
                                            r8 = r22;
                                            r23 = r24;
                                            objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
                                            r20 = r8;
                                            r2 = r23;
                                            thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                            if (thM7784exceptionOrNullimpl == null) {
                                                return objM7781constructorimpl;
                                            }
                                            this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                            MasrofRepository masrofRepository16 = this.repository;
                                            AuditLogEntity auditLogEntity14 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                            c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                            c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                            obj2 = null;
                                            c40601.L$4 = null;
                                            c40601.L$5 = null;
                                            c40601.L$6 = null;
                                            c40601.L$7 = null;
                                            c40601.L$8 = null;
                                            c40601.L$9 = null;
                                            c40601.L$10 = null;
                                            c40601.L$11 = null;
                                            c40601.Z$0 = r2;
                                            c40601.I$0 = 0;
                                            c40601.label = 9;
                                        }
                                        break;
                                    }
                                }
                            }
                            return coroutine_suspended;
                        } catch (Throwable th8) {
                            th = th8;
                            r9 = z7;
                            r25 = str21;
                            Result.Companion companion8 = Result.INSTANCE;
                            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                            r20 = r25;
                            str5 = str3;
                            r2 = r9;
                            thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                            if (thM7784exceptionOrNullimpl == null) {
                                return objM7781constructorimpl;
                            }
                            this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                            MasrofRepository masrofRepository17 = this.repository;
                            AuditLogEntity auditLogEntity15 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                            c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                            c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                            obj2 = null;
                            c40601.L$4 = null;
                            c40601.L$5 = null;
                            c40601.L$6 = null;
                            c40601.L$7 = null;
                            c40601.L$8 = null;
                            c40601.L$9 = null;
                            c40601.L$10 = null;
                            c40601.L$11 = null;
                            c40601.Z$0 = r2;
                            c40601.I$0 = 0;
                            c40601.label = 9;
                        }
                        break;
                    case 5:
                        int i6 = c40601.I$0;
                        boolean z8 = c40601.Z$0;
                        FirebaseCloudSync.AccessDecision accessDecision5 = (FirebaseCloudSync.AccessDecision) c40601.L$6;
                        String str22 = (String) c40601.L$5;
                        UserEntity userEntity16 = (UserEntity) c40601.L$4;
                        MasrofViewModel masrofViewModel13 = (MasrofViewModel) c40601.L$3;
                        String str23 = (String) c40601.L$2;
                        String str24 = (String) c40601.L$1;
                        Context context8 = (Context) c40601.L$0;
                        try {
                            ResultKt.throwOnFailure(obj9);
                            masrofViewModel3 = masrofViewModel13;
                            str3 = str23;
                            r12 = z8;
                            r5 = str24;
                            context3 = context8;
                            userEntity4 = userEntity16;
                            str8 = str22;
                            accessDecision2 = accessDecision5;
                            i = i6;
                            obj5 = obj9;
                            map = (Map) obj5;
                            currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            if (currentUser != null) {
                                r13 = email;
                                r13 = r5;
                            } else {
                                r13 = email;
                                r13 = r5;
                            }
                            r13 = email;
                            obj6 = map.get("fullName");
                            if (obj6 != null) {
                                r21 = r13;
                            } else {
                                r21 = r13;
                            }
                            obj7 = map.get("role");
                            if (obj7 != null) {
                                string = obj7.toString();
                            } else {
                                string = null;
                            }
                            appRoleRoleFrom = masrofViewModel3.roleFrom(string);
                            long jHashCode5 = str8.hashCode();
                            accessDecision3 = accessDecision2;
                            obj8 = map.get(HintConstants.AUTOFILL_HINT_USERNAME);
                            if (obj8 != null) {
                                r19 = r13;
                            } else {
                                r19 = r13;
                            }
                            authenticatedUser3 = new AuthenticatedUser(jHashCode5, r19, r21, appRoleRoleFrom);
                            UserSession.INSTANCE.setCurrent(authenticatedUser3);
                            c40601.L$0 = context3;
                            c40601.L$1 = r5;
                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                            c40601.L$3 = masrofViewModel3;
                            c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity4);
                            c40601.L$5 = SpillingKt.nullOutSpilledVariable(str8);
                            c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision3);
                            c40601.L$7 = SpillingKt.nullOutSpilledVariable(map);
                            c40601.L$8 = SpillingKt.nullOutSpilledVariable(r21);
                            c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                            c40601.L$10 = SpillingKt.nullOutSpilledVariable(r13);
                            c40601.L$11 = authenticatedUser3;
                            c40601.Z$0 = r12;
                            c40601.I$0 = i;
                            c40601.label = 6;
                            if (masrofViewModel3.cacheLocalUser(authenticatedUser3, str3, c40601) != coroutine_suspended) {
                                MasrofViewModel masrofViewModel14 = masrofViewModel3;
                                map2 = map;
                                str9 = str8;
                                masrofViewModel4 = masrofViewModel14;
                                r15 = r5;
                                str10 = str3;
                                r7 = r12;
                                userEntity5 = userEntity4;
                                r14 = r13;
                                r16 = r21;
                                accessDecision4 = accessDecision3;
                                str11 = str9;
                                if (r7 != 0) {
                                    RememberedLogin.INSTANCE.save(context3, authenticatedUser3.getUsername());
                                } else {
                                    RememberedLogin.INSTANCE.clear(context3);
                                }
                                masrofRepository2 = masrofViewModel4.repository;
                                auditLogEntity2 = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "LOGIN_SUCCESS", "تسجيل دخول Firebase ناجح", 0L, 33, null);
                                r37 = r14;
                                c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                c40601.L$1 = r15;
                                c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                                c40601.L$3 = masrofViewModel4;
                                c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity5);
                                c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                                c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                                c40601.L$8 = SpillingKt.nullOutSpilledVariable(r16);
                                c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                c40601.L$10 = SpillingKt.nullOutSpilledVariable(r37);
                                c40601.L$11 = authenticatedUser3;
                                c40601.Z$0 = r7;
                                c40601.I$0 = i;
                                c40601.label = 7;
                                if (masrofRepository2.addAudit(auditLogEntity2, c40601) == coroutine_suspended) {
                                    userEntity6 = userEntity5;
                                    r17 = r37;
                                    r25 = r7;
                                    r110 = r16;
                                    r18 = r15;
                                    firebaseCloudSync = masrofViewModel4.cloud;
                                    UserEntity userEntity17 = userEntity6;
                                    masrofRepository3 = masrofViewModel4.repository;
                                    c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                    c40601.L$1 = r18;
                                    c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                                    c40601.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                    c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity17);
                                    c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                    c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                                    c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                                    c40601.L$8 = SpillingKt.nullOutSpilledVariable(r110);
                                    c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                    c40601.L$10 = SpillingKt.nullOutSpilledVariable(r17);
                                    c40601.L$11 = authenticatedUser3;
                                    c40601.Z$0 = r25;
                                    c40601.I$0 = i;
                                    c40601.label = 8;
                                    if (firebaseCloudSync.syncDocuments(masrofRepository3, c40601) != coroutine_suspended) {
                                        authenticatedUser4 = authenticatedUser3;
                                        str12 = str10;
                                        r22 = r18;
                                        r24 = r25;
                                        authenticatedUser2 = authenticatedUser4;
                                        str5 = str12;
                                        r8 = r22;
                                        r23 = r24;
                                        objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
                                        r20 = r8;
                                        r2 = r23;
                                        thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                        if (thM7784exceptionOrNullimpl == null) {
                                            return objM7781constructorimpl;
                                        }
                                        this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                        MasrofRepository masrofRepository18 = this.repository;
                                        AuditLogEntity auditLogEntity16 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                        c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                        c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                        c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                        c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                        obj2 = null;
                                        c40601.L$4 = null;
                                        c40601.L$5 = null;
                                        c40601.L$6 = null;
                                        c40601.L$7 = null;
                                        c40601.L$8 = null;
                                        c40601.L$9 = null;
                                        c40601.L$10 = null;
                                        c40601.L$11 = null;
                                        c40601.Z$0 = r2;
                                        c40601.I$0 = 0;
                                        c40601.label = 9;
                                    }
                                    break;
                                }
                            }
                        } catch (Throwable th9) {
                            th = th9;
                            str3 = str23;
                            r9 = z8;
                            r25 = str24;
                            context3 = context8;
                            Result.Companion companion9 = Result.INSTANCE;
                            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                            r20 = r25;
                            str5 = str3;
                            r2 = r9;
                            thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                            if (thM7784exceptionOrNullimpl == null) {
                                return objM7781constructorimpl;
                            }
                            this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                            MasrofRepository masrofRepository19 = this.repository;
                            AuditLogEntity auditLogEntity17 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                            c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                            c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                            obj2 = null;
                            c40601.L$4 = null;
                            c40601.L$5 = null;
                            c40601.L$6 = null;
                            c40601.L$7 = null;
                            c40601.L$8 = null;
                            c40601.L$9 = null;
                            c40601.L$10 = null;
                            c40601.L$11 = null;
                            c40601.Z$0 = r2;
                            c40601.I$0 = 0;
                            c40601.label = 9;
                            break;
                        }
                        return coroutine_suspended;
                    case 6:
                        int i7 = c40601.I$0;
                        boolean z9 = c40601.Z$0;
                        AuthenticatedUser authenticatedUser5 = (AuthenticatedUser) c40601.L$11;
                        String str25 = (String) c40601.L$10;
                        AppRole appRole = (AppRole) c40601.L$9;
                        String str26 = (String) c40601.L$8;
                        Map map3 = (Map) c40601.L$7;
                        FirebaseCloudSync.AccessDecision accessDecision6 = (FirebaseCloudSync.AccessDecision) c40601.L$6;
                        String str27 = (String) c40601.L$5;
                        UserEntity userEntity18 = (UserEntity) c40601.L$4;
                        MasrofViewModel masrofViewModel15 = (MasrofViewModel) c40601.L$3;
                        str10 = (String) c40601.L$2;
                        String str28 = (String) c40601.L$1;
                        context5 = (Context) c40601.L$0;
                        try {
                            ResultKt.throwOnFailure(obj9);
                            appRoleRoleFrom = appRole;
                            str9 = str27;
                            masrofViewModel4 = masrofViewModel15;
                            r16 = str26;
                            accessDecision4 = accessDecision6;
                            userEntity5 = userEntity18;
                            context3 = context5;
                            map2 = map3;
                            r14 = str25;
                            authenticatedUser3 = authenticatedUser5;
                            i = i7;
                            r7 = z9;
                            r15 = str28;
                            str11 = str9;
                            if (r7 != 0) {
                                RememberedLogin.INSTANCE.save(context3, authenticatedUser3.getUsername());
                            } else {
                                RememberedLogin.INSTANCE.clear(context3);
                            }
                            masrofRepository2 = masrofViewModel4.repository;
                            auditLogEntity2 = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "LOGIN_SUCCESS", "تسجيل دخول Firebase ناجح", 0L, 33, null);
                            r37 = r14;
                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                            c40601.L$1 = r15;
                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                            c40601.L$3 = masrofViewModel4;
                            c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity5);
                            c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                            c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                            c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                            c40601.L$8 = SpillingKt.nullOutSpilledVariable(r16);
                            c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                            c40601.L$10 = SpillingKt.nullOutSpilledVariable(r37);
                            c40601.L$11 = authenticatedUser3;
                            c40601.Z$0 = r7;
                            c40601.I$0 = i;
                            c40601.label = 7;
                            if (masrofRepository2.addAudit(auditLogEntity2, c40601) == coroutine_suspended) {
                                userEntity6 = userEntity5;
                                r17 = r37;
                                r25 = r7;
                                r110 = r16;
                                r18 = r15;
                                firebaseCloudSync = masrofViewModel4.cloud;
                                UserEntity userEntity19 = userEntity6;
                                masrofRepository3 = masrofViewModel4.repository;
                                c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                c40601.L$1 = r18;
                                c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                                c40601.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity19);
                                c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                                c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                                c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                                c40601.L$8 = SpillingKt.nullOutSpilledVariable(r110);
                                c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                                c40601.L$10 = SpillingKt.nullOutSpilledVariable(r17);
                                c40601.L$11 = authenticatedUser3;
                                c40601.Z$0 = r25;
                                c40601.I$0 = i;
                                c40601.label = 8;
                                if (firebaseCloudSync.syncDocuments(masrofRepository3, c40601) != coroutine_suspended) {
                                    authenticatedUser4 = authenticatedUser3;
                                    str12 = str10;
                                    r22 = r18;
                                    r24 = r25;
                                    authenticatedUser2 = authenticatedUser4;
                                    str5 = str12;
                                    r8 = r22;
                                    r23 = r24;
                                    objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
                                    r20 = r8;
                                    r2 = r23;
                                    thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                    if (thM7784exceptionOrNullimpl == null) {
                                        return objM7781constructorimpl;
                                    }
                                    this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                    MasrofRepository masrofRepository110 = this.repository;
                                    AuditLogEntity auditLogEntity18 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                    c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                    c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                    c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                    c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                    obj2 = null;
                                    c40601.L$4 = null;
                                    c40601.L$5 = null;
                                    c40601.L$6 = null;
                                    c40601.L$7 = null;
                                    c40601.L$8 = null;
                                    c40601.L$9 = null;
                                    c40601.L$10 = null;
                                    c40601.L$11 = null;
                                    c40601.Z$0 = r2;
                                    c40601.I$0 = 0;
                                    c40601.label = 9;
                                }
                                break;
                            }
                        } catch (Throwable th10) {
                            th = th10;
                            r9 = z9 ? 1 : 0;
                            str3 = str10;
                            r25 = str28;
                            context3 = context5;
                            Result.Companion companion10 = Result.INSTANCE;
                            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                            r20 = r25;
                            str5 = str3;
                            r2 = r9;
                            thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                            if (thM7784exceptionOrNullimpl == null) {
                                return objM7781constructorimpl;
                            }
                            this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                            MasrofRepository masrofRepository111 = this.repository;
                            AuditLogEntity auditLogEntity19 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                            c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                            c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                            obj2 = null;
                            c40601.L$4 = null;
                            c40601.L$5 = null;
                            c40601.L$6 = null;
                            c40601.L$7 = null;
                            c40601.L$8 = null;
                            c40601.L$9 = null;
                            c40601.L$10 = null;
                            c40601.L$11 = null;
                            c40601.Z$0 = r2;
                            c40601.I$0 = 0;
                            c40601.label = 9;
                            break;
                        }
                        return coroutine_suspended;
                    case 7:
                        int i8 = c40601.I$0;
                        boolean z10 = c40601.Z$0;
                        authenticatedUser3 = (AuthenticatedUser) c40601.L$11;
                        String str29 = (String) c40601.L$10;
                        AppRole appRole2 = (AppRole) c40601.L$9;
                        String str30 = (String) c40601.L$8;
                        map2 = (Map) c40601.L$7;
                        accessDecision4 = (FirebaseCloudSync.AccessDecision) c40601.L$6;
                        String str31 = (String) c40601.L$5;
                        UserEntity userEntity20 = (UserEntity) c40601.L$4;
                        masrofViewModel4 = (MasrofViewModel) c40601.L$3;
                        str3 = (String) c40601.L$2;
                        String str32 = (String) c40601.L$1;
                        context5 = (Context) c40601.L$0;
                        try {
                            ResultKt.throwOnFailure(obj9);
                            i = i8;
                            r25 = z10;
                            appRoleRoleFrom = appRole2;
                            str11 = str31;
                            context3 = context5;
                            str10 = str3;
                            userEntity6 = userEntity20;
                            r18 = str32;
                            r17 = str29;
                            r110 = str30;
                            firebaseCloudSync = masrofViewModel4.cloud;
                            UserEntity userEntity110 = userEntity6;
                            masrofRepository3 = masrofViewModel4.repository;
                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                            c40601.L$1 = r18;
                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str10);
                            c40601.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                            c40601.L$4 = SpillingKt.nullOutSpilledVariable(userEntity110);
                            c40601.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                            c40601.L$6 = SpillingKt.nullOutSpilledVariable(accessDecision4);
                            c40601.L$7 = SpillingKt.nullOutSpilledVariable(map2);
                            c40601.L$8 = SpillingKt.nullOutSpilledVariable(r110);
                            c40601.L$9 = SpillingKt.nullOutSpilledVariable(appRoleRoleFrom);
                            c40601.L$10 = SpillingKt.nullOutSpilledVariable(r17);
                            c40601.L$11 = authenticatedUser3;
                            c40601.Z$0 = r25;
                            c40601.I$0 = i;
                            c40601.label = 8;
                            if (firebaseCloudSync.syncDocuments(masrofRepository3, c40601) != coroutine_suspended) {
                                authenticatedUser4 = authenticatedUser3;
                                str12 = str10;
                                r22 = r18;
                                r24 = r25;
                                authenticatedUser2 = authenticatedUser4;
                                str5 = str12;
                                r8 = r22;
                                r23 = r24;
                                objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
                                r20 = r8;
                                r2 = r23;
                                thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                if (thM7784exceptionOrNullimpl == null) {
                                    return objM7781constructorimpl;
                                }
                                this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                                MasrofRepository masrofRepository112 = this.repository;
                                AuditLogEntity auditLogEntity110 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                                c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                                c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                                c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                                obj2 = null;
                                c40601.L$4 = null;
                                c40601.L$5 = null;
                                c40601.L$6 = null;
                                c40601.L$7 = null;
                                c40601.L$8 = null;
                                c40601.L$9 = null;
                                c40601.L$10 = null;
                                c40601.L$11 = null;
                                c40601.Z$0 = r2;
                                c40601.I$0 = 0;
                                c40601.label = 9;
                            }
                            break;
                        } catch (Throwable th11) {
                            th = th11;
                            r9 = z10;
                            r25 = str32;
                            context3 = context5;
                            Result.Companion companion11 = Result.INSTANCE;
                            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                            r20 = r25;
                            str5 = str3;
                            r2 = r9;
                            thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                            if (thM7784exceptionOrNullimpl == null) {
                                return objM7781constructorimpl;
                            }
                            this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                            MasrofRepository masrofRepository113 = this.repository;
                            AuditLogEntity auditLogEntity111 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                            c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                            c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                            obj2 = null;
                            c40601.L$4 = null;
                            c40601.L$5 = null;
                            c40601.L$6 = null;
                            c40601.L$7 = null;
                            c40601.L$8 = null;
                            c40601.L$9 = null;
                            c40601.L$10 = null;
                            c40601.L$11 = null;
                            c40601.Z$0 = r2;
                            c40601.I$0 = 0;
                            c40601.label = 9;
                            break;
                        }
                        return coroutine_suspended;
                    case 8:
                        int i9 = c40601.I$0;
                        boolean z11 = c40601.Z$0;
                        authenticatedUser4 = (AuthenticatedUser) c40601.L$11;
                        str12 = (String) c40601.L$2;
                        String str33 = (String) c40601.L$1;
                        context3 = (Context) c40601.L$0;
                        try {
                            ResultKt.throwOnFailure(obj9);
                            r24 = z11;
                            r22 = str33;
                            authenticatedUser2 = authenticatedUser4;
                            str5 = str12;
                            r8 = r22;
                            r23 = r24;
                            objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
                            r20 = r8;
                            r2 = r23;
                        } catch (Throwable th12) {
                            th = th12;
                            r9 = z11;
                            r25 = str33;
                            str3 = str12;
                            Result.Companion companion12 = Result.INSTANCE;
                            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                            r20 = r25;
                            str5 = str3;
                            r2 = r9;
                            thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                            if (thM7784exceptionOrNullimpl == null) {
                                return objM7781constructorimpl;
                            }
                            this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                            MasrofRepository masrofRepository114 = this.repository;
                            AuditLogEntity auditLogEntity112 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                            c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                            c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                            c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                            c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                            obj2 = null;
                            c40601.L$4 = null;
                            c40601.L$5 = null;
                            c40601.L$6 = null;
                            c40601.L$7 = null;
                            c40601.L$8 = null;
                            c40601.L$9 = null;
                            c40601.L$10 = null;
                            c40601.L$11 = null;
                            c40601.Z$0 = r2;
                            c40601.I$0 = 0;
                            c40601.label = 9;
                        }
                        thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                        if (thM7784exceptionOrNullimpl == null) {
                            return objM7781constructorimpl;
                        }
                        this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                        MasrofRepository masrofRepository115 = this.repository;
                        AuditLogEntity auditLogEntity113 = new AuditLogEntity(0L, null, r20, "LOGIN_FAILED", "محاولة دخول Firebase فاشلة", 0L, 33, null);
                        c40601.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                        c40601.L$1 = SpillingKt.nullOutSpilledVariable(r20);
                        c40601.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                        c40601.L$3 = SpillingKt.nullOutSpilledVariable(thM7784exceptionOrNullimpl);
                        obj2 = null;
                        c40601.L$4 = null;
                        c40601.L$5 = null;
                        c40601.L$6 = null;
                        c40601.L$7 = null;
                        c40601.L$8 = null;
                        c40601.L$9 = null;
                        c40601.L$10 = null;
                        c40601.L$11 = null;
                        c40601.Z$0 = r2;
                        c40601.I$0 = 0;
                        c40601.label = 9;
                        break;
                    case 9:
                        int i10 = c40601.I$0;
                        boolean z12 = c40601.Z$0;
                        ResultKt.throwOnFailure(obj9);
                        obj2 = null;
                        return obj2;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th13) {
                th = th13;
                context3 = context2;
                r9 = r25;
                r25 = obj;
                str3 = str4;
            }
        } catch (Throwable th14) {
            th = th14;
            r9 = r25;
            str3 = str10;
            r25 = r18;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0105, code lost:
    
        if (r0.insertUser(r9, r2) == r3) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x012f, code lost:
    
        if (r0.updateUser(r9, r2) == r3) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object cacheLocalUser(AuthenticatedUser authenticatedUser, String str, Continuation<? super Unit> continuation) {
        C40621 c40621;
        AuthenticatedUser authenticatedUser2;
        String str2;
        String str3;
        if (continuation instanceof C40621) {
            c40621 = (C40621) continuation;
            if ((c40621.label & Integer.MIN_VALUE) != 0) {
                c40621.label -= Integer.MIN_VALUE;
            } else {
                c40621 = new C40621(continuation);
            }
        } else {
            c40621 = new C40621(continuation);
        }
        Object objFindActiveUser = c40621.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c40621.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objFindActiveUser);
            MasrofRepository masrofRepository = this.repository;
            String username = authenticatedUser.getUsername();
            authenticatedUser2 = authenticatedUser;
            c40621.L$0 = authenticatedUser2;
            c40621.L$1 = str;
            c40621.label = 1;
            objFindActiveUser = masrofRepository.findActiveUser(username, c40621);
            if (objFindActiveUser != coroutine_suspended) {
                str2 = str;
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i == 2) {
                ResultKt.throwOnFailure(objFindActiveUser);
                return Unit.INSTANCE;
            }
            if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objFindActiveUser);
            return Unit.INSTANCE;
        }
        str2 = (String) c40621.L$1;
        authenticatedUser2 = (AuthenticatedUser) c40621.L$0;
        ResultKt.throwOnFailure(objFindActiveUser);
        UserEntity userEntity = (UserEntity) objFindActiveUser;
        int i2 = WhenMappings.$EnumSwitchMapping$0[authenticatedUser2.getRole().ordinal()];
        if (i2 == 1) {
            str3 = "SYSTEM_ADMIN";
        } else if (i2 != 2) {
            str3 = i2 != 3 ? "USER" : "ACCOUNTANT";
        } else {
            str3 = "FINANCE_DIRECTOR";
        }
        String str4 = str3;
        UserEntity userEntity2 = new UserEntity(userEntity != null ? userEntity.getId() : 0L, authenticatedUser2.getUsername(), AuthSecurity.INSTANCE.hash(str2), authenticatedUser2.getFullName(), str4, true, userEntity != null ? userEntity.getCreatedAt() : System.currentTimeMillis());
        MasrofRepository masrofRepository2 = this.repository;
        if (userEntity == null) {
            c40621.L$0 = SpillingKt.nullOutSpilledVariable(authenticatedUser2);
            c40621.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            c40621.L$2 = SpillingKt.nullOutSpilledVariable(userEntity);
            c40621.L$3 = SpillingKt.nullOutSpilledVariable(str4);
            c40621.L$4 = SpillingKt.nullOutSpilledVariable(userEntity2);
            c40621.label = 2;
        } else {
            c40621.L$0 = SpillingKt.nullOutSpilledVariable(authenticatedUser2);
            c40621.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            c40621.L$2 = SpillingKt.nullOutSpilledVariable(userEntity);
            c40621.L$3 = SpillingKt.nullOutSpilledVariable(str4);
            c40621.L$4 = SpillingKt.nullOutSpilledVariable(userEntity2);
            c40621.label = 3;
        }
        return coroutine_suspended;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0143 A[Catch: all -> 0x026c, TryCatch #0 {all -> 0x026c, blocks: (B:16:0x005d, B:65:0x0254, B:21:0x008c, B:62:0x01fe, B:24:0x00b0, B:47:0x0173, B:49:0x0193, B:54:0x01aa, B:56:0x01b2, B:58:0x01b8, B:53:0x019d, B:27:0x00d0, B:41:0x013b, B:43:0x0143, B:66:0x0259, B:67:0x026b, B:30:0x00e7, B:37:0x0117, B:33:0x00f5), top: B:75:0x002f }] */
    /* JADX WARN: Code duplicated, block: B:45:0x0169  */
    /* JADX WARN: Code duplicated, block: B:46:0x016b  */
    /* JADX WARN: Code duplicated, block: B:49:0x0193 A[Catch: all -> 0x026c, TryCatch #0 {all -> 0x026c, blocks: (B:16:0x005d, B:65:0x0254, B:21:0x008c, B:62:0x01fe, B:24:0x00b0, B:47:0x0173, B:49:0x0193, B:54:0x01aa, B:56:0x01b2, B:58:0x01b8, B:53:0x019d, B:27:0x00d0, B:41:0x013b, B:43:0x0143, B:66:0x0259, B:67:0x026b, B:30:0x00e7, B:37:0x0117, B:33:0x00f5), top: B:75:0x002f }] */
    /* JADX WARN: Code duplicated, block: B:53:0x019d A[Catch: all -> 0x026c, TryCatch #0 {all -> 0x026c, blocks: (B:16:0x005d, B:65:0x0254, B:21:0x008c, B:62:0x01fe, B:24:0x00b0, B:47:0x0173, B:49:0x0193, B:54:0x01aa, B:56:0x01b2, B:58:0x01b8, B:53:0x019d, B:27:0x00d0, B:41:0x013b, B:43:0x0143, B:66:0x0259, B:67:0x026b, B:30:0x00e7, B:37:0x0117, B:33:0x00f5), top: B:75:0x002f }] */
    /* JADX WARN: Code duplicated, block: B:56:0x01b2 A[Catch: all -> 0x026c, TryCatch #0 {all -> 0x026c, blocks: (B:16:0x005d, B:65:0x0254, B:21:0x008c, B:62:0x01fe, B:24:0x00b0, B:47:0x0173, B:49:0x0193, B:54:0x01aa, B:56:0x01b2, B:58:0x01b8, B:53:0x019d, B:27:0x00d0, B:41:0x013b, B:43:0x0143, B:66:0x0259, B:67:0x026b, B:30:0x00e7, B:37:0x0117, B:33:0x00f5), top: B:75:0x002f }] */
    /* JADX WARN: Code duplicated, block: B:57:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:60:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:61:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:66:0x0259 A[Catch: all -> 0x026c, TryCatch #0 {all -> 0x026c, blocks: (B:16:0x005d, B:65:0x0254, B:21:0x008c, B:62:0x01fe, B:24:0x00b0, B:47:0x0173, B:49:0x0193, B:54:0x01aa, B:56:0x01b2, B:58:0x01b8, B:53:0x019d, B:27:0x00d0, B:41:0x013b, B:43:0x0143, B:66:0x0259, B:67:0x026b, B:30:0x00e7, B:37:0x0117, B:33:0x00f5), top: B:75:0x002f }] */
    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0251, code lost:
    
        if (r3.addAudit(r15, r4) == r5) goto L64;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object authenticateFirstLogin(Context context, String str, String str2, Continuation<? super AuthenticatedUser> continuation) {
        C40611 c40611;
        Object objM7781constructorimpl;
        String str3;
        int i;
        Context context2;
        MasrofViewModel masrofViewModel;
        String str4;
        String str5;
        FirebaseCloudSync.AccessDecision accessDecision;
        Object objCurrentProfile;
        String str6;
        FirebaseCloudSync.AccessDecision accessDecision2;
        Context context3;
        String str7;
        String str8;
        MasrofViewModel masrofViewModel2;
        Map map;
        AuthenticatedUser authenticatedUser;
        String str9;
        Object obj;
        String string;
        Object obj2;
        String string2;
        String str10;
        FirebaseCloudSync.AccessDecision accessDecision3;
        int i2;
        AuthenticatedUser authenticatedUser2;
        String str11 = str2;
        if (continuation instanceof C40611) {
            c40611 = (C40611) continuation;
            if ((c40611.label & Integer.MIN_VALUE) != 0) {
                c40611.label -= Integer.MIN_VALUE;
            } else {
                c40611 = new C40611(continuation);
            }
        } else {
            c40611 = new C40611(continuation);
        }
        Object objCreateFirstAccount = c40611.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c40611.label;
        try {
            if (i3 == 0) {
                ResultKt.throwOnFailure(objCreateFirstAccount);
                this.lastAuthError = null;
                Result.Companion companion = Result.INSTANCE;
                MasrofViewModel masrofViewModel3 = this;
                FirebaseCloudSync firebaseCloudSync = this.cloud;
                c40611.L$0 = context;
                c40611.L$1 = str;
                c40611.L$2 = str11;
                c40611.L$3 = this;
                c40611.I$0 = 0;
                c40611.label = 1;
                objCreateFirstAccount = firebaseCloudSync.createFirstAccount(str, str11, c40611);
                if (objCreateFirstAccount != coroutine_suspended) {
                    str3 = str;
                    i = 0;
                    context2 = context;
                    masrofViewModel = this;
                }
                return coroutine_suspended;
            }
            if (i3 == 1) {
                i = c40611.I$0;
                MasrofViewModel masrofViewModel4 = (MasrofViewModel) c40611.L$3;
                String str12 = (String) c40611.L$2;
                str3 = (String) c40611.L$1;
                context2 = (Context) c40611.L$0;
                ResultKt.throwOnFailure(objCreateFirstAccount);
                masrofViewModel = masrofViewModel4;
                str11 = str12;
            } else {
                if (i3 == 2) {
                    i = c40611.I$0;
                    str5 = (String) c40611.L$4;
                    masrofViewModel = (MasrofViewModel) c40611.L$3;
                    str4 = (String) c40611.L$2;
                    str3 = (String) c40611.L$1;
                    context2 = (Context) c40611.L$0;
                    ResultKt.throwOnFailure(objCreateFirstAccount);
                    accessDecision = (FirebaseCloudSync.AccessDecision) objCreateFirstAccount;
                    if (accessDecision.getAllowed()) {
                        masrofViewModel.cloud.signOut();
                        throw new IllegalStateException(accessDecision.getMessage().toString());
                    }
                    masrofViewModel.isManagerSession = accessDecision.isManager();
                    FirebaseCloudSync firebaseCloudSync2 = masrofViewModel.cloud;
                    c40611.L$0 = SpillingKt.nullOutSpilledVariable(context2);
                    c40611.L$1 = str3;
                    c40611.L$2 = str4;
                    c40611.L$3 = masrofViewModel;
                    c40611.L$4 = str5;
                    c40611.L$5 = SpillingKt.nullOutSpilledVariable(accessDecision);
                    c40611.I$0 = i;
                    c40611.label = 3;
                    objCurrentProfile = firebaseCloudSync2.currentProfile(c40611);
                    if (objCurrentProfile == coroutine_suspended) {
                        str6 = str5;
                        accessDecision2 = accessDecision;
                        objCreateFirstAccount = objCurrentProfile;
                        context3 = context2;
                        str7 = str3;
                        str8 = str4;
                        masrofViewModel2 = masrofViewModel;
                        map = (Map) objCreateFirstAccount;
                        str9 = str7;
                        long jHashCode = str6.hashCode();
                        String string3 = StringsKt.trim((CharSequence) str9).toString();
                        obj = map.get("fullName");
                        if (obj != null) {
                            string = StringsKt.trim((CharSequence) str9).toString();
                        } else {
                            string = StringsKt.trim((CharSequence) str9).toString();
                        }
                        String str13 = string;
                        obj2 = map.get("role");
                        if (obj2 != null) {
                            string2 = obj2.toString();
                        } else {
                            string2 = null;
                        }
                        authenticatedUser = new AuthenticatedUser(jHashCode, string3, str13, masrofViewModel2.roleFrom(string2));
                        UserSession.INSTANCE.setCurrent(authenticatedUser);
                        c40611.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                        c40611.L$1 = SpillingKt.nullOutSpilledVariable(str9);
                        c40611.L$2 = SpillingKt.nullOutSpilledVariable(str8);
                        c40611.L$3 = masrofViewModel2;
                        c40611.L$4 = SpillingKt.nullOutSpilledVariable(str6);
                        c40611.L$5 = SpillingKt.nullOutSpilledVariable(accessDecision2);
                        c40611.L$6 = SpillingKt.nullOutSpilledVariable(map);
                        c40611.L$7 = authenticatedUser;
                        c40611.I$0 = i;
                        c40611.label = 4;
                        if (masrofViewModel2.cacheLocalUser(authenticatedUser, str8, c40611) != coroutine_suspended) {
                            str10 = str9;
                            accessDecision3 = accessDecision2;
                            i2 = i;
                            authenticatedUser2 = authenticatedUser;
                            MasrofRepository masrofRepository = masrofViewModel2.repository;
                            AuditLogEntity auditLogEntity = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser2.getId()), authenticatedUser2.getUsername(), "FIRST_LOGIN_EMAIL_USERNAME", "تهيئة الدخول الأولى بالبريد واسم المستخدم", 0L, 33, null);
                            c40611.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                            c40611.L$1 = SpillingKt.nullOutSpilledVariable(str10);
                            c40611.L$2 = SpillingKt.nullOutSpilledVariable(str8);
                            c40611.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel2);
                            c40611.L$4 = SpillingKt.nullOutSpilledVariable(str6);
                            c40611.L$5 = SpillingKt.nullOutSpilledVariable(accessDecision3);
                            c40611.L$6 = SpillingKt.nullOutSpilledVariable(map);
                            c40611.L$7 = authenticatedUser2;
                            c40611.I$0 = i2;
                            c40611.label = 5;
                        }
                    }
                    return coroutine_suspended;
                }
                if (i3 == 3) {
                    i = c40611.I$0;
                    accessDecision2 = (FirebaseCloudSync.AccessDecision) c40611.L$5;
                    String str14 = (String) c40611.L$4;
                    MasrofViewModel masrofViewModel5 = (MasrofViewModel) c40611.L$3;
                    String str15 = (String) c40611.L$2;
                    String str16 = (String) c40611.L$1;
                    Context context4 = (Context) c40611.L$0;
                    ResultKt.throwOnFailure(objCreateFirstAccount);
                    context3 = context4;
                    str7 = str16;
                    str8 = str15;
                    masrofViewModel2 = masrofViewModel5;
                    str6 = str14;
                    map = (Map) objCreateFirstAccount;
                    str9 = str7;
                    long jHashCode2 = str6.hashCode();
                    String string4 = StringsKt.trim((CharSequence) str9).toString();
                    obj = map.get("fullName");
                    if (obj != null || (string = obj.toString()) == null) {
                        string = StringsKt.trim((CharSequence) str9).toString();
                    }
                    String str17 = string;
                    obj2 = map.get("role");
                    if (obj2 != null) {
                        string2 = obj2.toString();
                    } else {
                        string2 = null;
                    }
                    authenticatedUser = new AuthenticatedUser(jHashCode2, string4, str17, masrofViewModel2.roleFrom(string2));
                    UserSession.INSTANCE.setCurrent(authenticatedUser);
                    c40611.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                    c40611.L$1 = SpillingKt.nullOutSpilledVariable(str9);
                    c40611.L$2 = SpillingKt.nullOutSpilledVariable(str8);
                    c40611.L$3 = masrofViewModel2;
                    c40611.L$4 = SpillingKt.nullOutSpilledVariable(str6);
                    c40611.L$5 = SpillingKt.nullOutSpilledVariable(accessDecision2);
                    c40611.L$6 = SpillingKt.nullOutSpilledVariable(map);
                    c40611.L$7 = authenticatedUser;
                    c40611.I$0 = i;
                    c40611.label = 4;
                    if (masrofViewModel2.cacheLocalUser(authenticatedUser, str8, c40611) != coroutine_suspended) {
                        str10 = str9;
                        accessDecision3 = accessDecision2;
                        i2 = i;
                        authenticatedUser2 = authenticatedUser;
                        MasrofRepository masrofRepository2 = masrofViewModel2.repository;
                        AuditLogEntity auditLogEntity2 = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser2.getId()), authenticatedUser2.getUsername(), "FIRST_LOGIN_EMAIL_USERNAME", "تهيئة الدخول الأولى بالبريد واسم المستخدم", 0L, 33, null);
                        c40611.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                        c40611.L$1 = SpillingKt.nullOutSpilledVariable(str10);
                        c40611.L$2 = SpillingKt.nullOutSpilledVariable(str8);
                        c40611.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel2);
                        c40611.L$4 = SpillingKt.nullOutSpilledVariable(str6);
                        c40611.L$5 = SpillingKt.nullOutSpilledVariable(accessDecision3);
                        c40611.L$6 = SpillingKt.nullOutSpilledVariable(map);
                        c40611.L$7 = authenticatedUser2;
                        c40611.I$0 = i2;
                        c40611.label = 5;
                    }
                    return coroutine_suspended;
                }
                if (i3 == 4) {
                    int i4 = c40611.I$0;
                    AuthenticatedUser authenticatedUser3 = (AuthenticatedUser) c40611.L$7;
                    map = (Map) c40611.L$6;
                    accessDecision3 = (FirebaseCloudSync.AccessDecision) c40611.L$5;
                    str6 = (String) c40611.L$4;
                    masrofViewModel2 = (MasrofViewModel) c40611.L$3;
                    str8 = (String) c40611.L$2;
                    str10 = (String) c40611.L$1;
                    context3 = (Context) c40611.L$0;
                    ResultKt.throwOnFailure(objCreateFirstAccount);
                    i2 = i4;
                    authenticatedUser2 = authenticatedUser3;
                    MasrofRepository masrofRepository3 = masrofViewModel2.repository;
                    AuditLogEntity auditLogEntity3 = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser2.getId()), authenticatedUser2.getUsername(), "FIRST_LOGIN_EMAIL_USERNAME", "تهيئة الدخول الأولى بالبريد واسم المستخدم", 0L, 33, null);
                    c40611.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                    c40611.L$1 = SpillingKt.nullOutSpilledVariable(str10);
                    c40611.L$2 = SpillingKt.nullOutSpilledVariable(str8);
                    c40611.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel2);
                    c40611.L$4 = SpillingKt.nullOutSpilledVariable(str6);
                    c40611.L$5 = SpillingKt.nullOutSpilledVariable(accessDecision3);
                    c40611.L$6 = SpillingKt.nullOutSpilledVariable(map);
                    c40611.L$7 = authenticatedUser2;
                    c40611.I$0 = i2;
                    c40611.label = 5;
                } else {
                    if (i3 != 5) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    int i5 = c40611.I$0;
                    authenticatedUser2 = (AuthenticatedUser) c40611.L$7;
                    ResultKt.throwOnFailure(objCreateFirstAccount);
                }
            }
            objM7781constructorimpl = Result.m7781constructorimpl(authenticatedUser2);
            Throwable thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
            if (thM7784exceptionOrNullimpl == null) {
                return objM7781constructorimpl;
            }
            this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
            return null;
            String str18 = (String) objCreateFirstAccount;
            FirebaseCloudSync firebaseCloudSync3 = masrofViewModel.cloud;
            c40611.L$0 = SpillingKt.nullOutSpilledVariable(context2);
            c40611.L$1 = str3;
            c40611.L$2 = str11;
            c40611.L$3 = masrofViewModel;
            c40611.L$4 = str18;
            c40611.I$0 = i;
            c40611.label = 2;
            Object objAuthorizeCurrentDevice = firebaseCloudSync3.authorizeCurrentDevice(context2, c40611);
            if (objAuthorizeCurrentDevice != coroutine_suspended) {
                str4 = str11;
                str5 = str18;
                objCreateFirstAccount = objAuthorizeCurrentDevice;
                accessDecision = (FirebaseCloudSync.AccessDecision) objCreateFirstAccount;
                if (accessDecision.getAllowed()) {
                    masrofViewModel.cloud.signOut();
                    throw new IllegalStateException(accessDecision.getMessage().toString());
                }
                masrofViewModel.isManagerSession = accessDecision.isManager();
                FirebaseCloudSync firebaseCloudSync4 = masrofViewModel.cloud;
                c40611.L$0 = SpillingKt.nullOutSpilledVariable(context2);
                c40611.L$1 = str3;
                c40611.L$2 = str4;
                c40611.L$3 = masrofViewModel;
                c40611.L$4 = str5;
                c40611.L$5 = SpillingKt.nullOutSpilledVariable(accessDecision);
                c40611.I$0 = i;
                c40611.label = 3;
                objCurrentProfile = firebaseCloudSync4.currentProfile(c40611);
                if (objCurrentProfile == coroutine_suspended) {
                    str6 = str5;
                    accessDecision2 = accessDecision;
                    objCreateFirstAccount = objCurrentProfile;
                    context3 = context2;
                    str7 = str3;
                    str8 = str4;
                    masrofViewModel2 = masrofViewModel;
                    map = (Map) objCreateFirstAccount;
                    str9 = str7;
                    long jHashCode3 = str6.hashCode();
                    String string5 = StringsKt.trim((CharSequence) str9).toString();
                    obj = map.get("fullName");
                    if (obj != null) {
                        string = StringsKt.trim((CharSequence) str9).toString();
                    } else {
                        string = StringsKt.trim((CharSequence) str9).toString();
                    }
                    String str19 = string;
                    obj2 = map.get("role");
                    if (obj2 != null) {
                        string2 = obj2.toString();
                    } else {
                        string2 = null;
                    }
                    authenticatedUser = new AuthenticatedUser(jHashCode3, string5, str19, masrofViewModel2.roleFrom(string2));
                    UserSession.INSTANCE.setCurrent(authenticatedUser);
                    c40611.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                    c40611.L$1 = SpillingKt.nullOutSpilledVariable(str9);
                    c40611.L$2 = SpillingKt.nullOutSpilledVariable(str8);
                    c40611.L$3 = masrofViewModel2;
                    c40611.L$4 = SpillingKt.nullOutSpilledVariable(str6);
                    c40611.L$5 = SpillingKt.nullOutSpilledVariable(accessDecision2);
                    c40611.L$6 = SpillingKt.nullOutSpilledVariable(map);
                    c40611.L$7 = authenticatedUser;
                    c40611.I$0 = i;
                    c40611.label = 4;
                    if (masrofViewModel2.cacheLocalUser(authenticatedUser, str8, c40611) != coroutine_suspended) {
                        str10 = str9;
                        accessDecision3 = accessDecision2;
                        i2 = i;
                        authenticatedUser2 = authenticatedUser;
                        MasrofRepository masrofRepository4 = masrofViewModel2.repository;
                        AuditLogEntity auditLogEntity4 = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser2.getId()), authenticatedUser2.getUsername(), "FIRST_LOGIN_EMAIL_USERNAME", "تهيئة الدخول الأولى بالبريد واسم المستخدم", 0L, 33, null);
                        c40611.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                        c40611.L$1 = SpillingKt.nullOutSpilledVariable(str10);
                        c40611.L$2 = SpillingKt.nullOutSpilledVariable(str8);
                        c40611.L$3 = SpillingKt.nullOutSpilledVariable(masrofViewModel2);
                        c40611.L$4 = SpillingKt.nullOutSpilledVariable(str6);
                        c40611.L$5 = SpillingKt.nullOutSpilledVariable(accessDecision3);
                        c40611.L$6 = SpillingKt.nullOutSpilledVariable(map);
                        c40611.L$7 = authenticatedUser2;
                        c40611.I$0 = i2;
                        c40611.label = 5;
                    }
                }
            }
            return coroutine_suspended;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0165  */
    /* JADX WARN: Code duplicated, block: B:42:0x0167  */
    /* JADX WARN: Code duplicated, block: B:45:0x018e A[Catch: all -> 0x0349, TryCatch #0 {all -> 0x0349, blocks: (B:13:0x0055, B:86:0x0328, B:16:0x007a, B:83:0x02ec, B:19:0x00a5, B:78:0x0285, B:80:0x0294, B:79:0x028f, B:22:0x00ca, B:58:0x01f9, B:60:0x01ff, B:70:0x022a, B:25:0x00ee, B:43:0x016d, B:45:0x018e, B:50:0x01a4, B:52:0x01ac, B:54:0x01b2, B:49:0x0198, B:28:0x010b, B:39:0x014d, B:31:0x0114, B:33:0x0123, B:35:0x0129, B:87:0x0331, B:88:0x033c, B:89:0x033d, B:90:0x0348), top: B:98:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:49:0x0198 A[Catch: all -> 0x0349, TryCatch #0 {all -> 0x0349, blocks: (B:13:0x0055, B:86:0x0328, B:16:0x007a, B:83:0x02ec, B:19:0x00a5, B:78:0x0285, B:80:0x0294, B:79:0x028f, B:22:0x00ca, B:58:0x01f9, B:60:0x01ff, B:70:0x022a, B:25:0x00ee, B:43:0x016d, B:45:0x018e, B:50:0x01a4, B:52:0x01ac, B:54:0x01b2, B:49:0x0198, B:28:0x010b, B:39:0x014d, B:31:0x0114, B:33:0x0123, B:35:0x0129, B:87:0x0331, B:88:0x033c, B:89:0x033d, B:90:0x0348), top: B:98:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:52:0x01ac A[Catch: all -> 0x0349, TryCatch #0 {all -> 0x0349, blocks: (B:13:0x0055, B:86:0x0328, B:16:0x007a, B:83:0x02ec, B:19:0x00a5, B:78:0x0285, B:80:0x0294, B:79:0x028f, B:22:0x00ca, B:58:0x01f9, B:60:0x01ff, B:70:0x022a, B:25:0x00ee, B:43:0x016d, B:45:0x018e, B:50:0x01a4, B:52:0x01ac, B:54:0x01b2, B:49:0x0198, B:28:0x010b, B:39:0x014d, B:31:0x0114, B:33:0x0123, B:35:0x0129, B:87:0x0331, B:88:0x033c, B:89:0x033d, B:90:0x0348), top: B:98:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:53:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:56:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:57:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:60:0x01ff A[Catch: all -> 0x0349, TryCatch #0 {all -> 0x0349, blocks: (B:13:0x0055, B:86:0x0328, B:16:0x007a, B:83:0x02ec, B:19:0x00a5, B:78:0x0285, B:80:0x0294, B:79:0x028f, B:22:0x00ca, B:58:0x01f9, B:60:0x01ff, B:70:0x022a, B:25:0x00ee, B:43:0x016d, B:45:0x018e, B:50:0x01a4, B:52:0x01ac, B:54:0x01b2, B:49:0x0198, B:28:0x010b, B:39:0x014d, B:31:0x0114, B:33:0x0123, B:35:0x0129, B:87:0x0331, B:88:0x033c, B:89:0x033d, B:90:0x0348), top: B:98:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:62:0x0217 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:63:0x0219  */
    /* JADX WARN: Code duplicated, block: B:65:0x021c  */
    /* JADX WARN: Code duplicated, block: B:67:0x0221  */
    /* JADX WARN: Code duplicated, block: B:68:0x0224  */
    /* JADX WARN: Code duplicated, block: B:69:0x0227  */
    /* JADX WARN: Code duplicated, block: B:72:0x026e  */
    /* JADX WARN: Code duplicated, block: B:73:0x0270  */
    /* JADX WARN: Code duplicated, block: B:76:0x027b  */
    /* JADX WARN: Code duplicated, block: B:78:0x0285 A[Catch: all -> 0x0349, TryCatch #0 {all -> 0x0349, blocks: (B:13:0x0055, B:86:0x0328, B:16:0x007a, B:83:0x02ec, B:19:0x00a5, B:78:0x0285, B:80:0x0294, B:79:0x028f, B:22:0x00ca, B:58:0x01f9, B:60:0x01ff, B:70:0x022a, B:25:0x00ee, B:43:0x016d, B:45:0x018e, B:50:0x01a4, B:52:0x01ac, B:54:0x01b2, B:49:0x0198, B:28:0x010b, B:39:0x014d, B:31:0x0114, B:33:0x0123, B:35:0x0129, B:87:0x0331, B:88:0x033c, B:89:0x033d, B:90:0x0348), top: B:98:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:79:0x028f A[Catch: all -> 0x0349, TryCatch #0 {all -> 0x0349, blocks: (B:13:0x0055, B:86:0x0328, B:16:0x007a, B:83:0x02ec, B:19:0x00a5, B:78:0x0285, B:80:0x0294, B:79:0x028f, B:22:0x00ca, B:58:0x01f9, B:60:0x01ff, B:70:0x022a, B:25:0x00ee, B:43:0x016d, B:45:0x018e, B:50:0x01a4, B:52:0x01ac, B:54:0x01b2, B:49:0x0198, B:28:0x010b, B:39:0x014d, B:31:0x0114, B:33:0x0123, B:35:0x0129, B:87:0x0331, B:88:0x033c, B:89:0x033d, B:90:0x0348), top: B:98:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Code duplicated, block: B:82:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:83:0x02ec A[Catch: all -> 0x0349, PHI: r0 r5 r6 r7 r8 r11 r12 r13 r14
      0x02ec: PHI (r0v21 int) = (r0v17 int), (r0v24 int) binds: [B:81:0x02e9, B:16:0x007a] A[DONT_GENERATE, DONT_INLINE]
      0x02ec: PHI (r5v13 boolean) = (r5v10 boolean), (r5v14 boolean) binds: [B:81:0x02e9, B:16:0x007a] A[DONT_GENERATE, DONT_INLINE]
      0x02ec: PHI (r6v16 com.mohammedalhzmi.masrofmanager.util.AuthenticatedUser) = 
      (r6v14 com.mohammedalhzmi.masrofmanager.util.AuthenticatedUser)
      (r6v19 com.mohammedalhzmi.masrofmanager.util.AuthenticatedUser)
     binds: [B:81:0x02e9, B:16:0x007a] A[DONT_GENERATE, DONT_INLINE]
      0x02ec: PHI (r7v21 java.util.Map) = (r7v14 java.util.Map), (r7v24 java.util.Map) binds: [B:81:0x02e9, B:16:0x007a] A[DONT_GENERATE, DONT_INLINE]
      0x02ec: PHI (r8v16 com.google.firebase.auth.FirebaseUser) = (r8v11 com.google.firebase.auth.FirebaseUser), (r8v19 com.google.firebase.auth.FirebaseUser) binds: [B:81:0x02e9, B:16:0x007a] A[DONT_GENERATE, DONT_INLINE]
      0x02ec: PHI (r11v20 java.lang.String) = (r11v15 java.lang.String), (r11v23 java.lang.String) binds: [B:81:0x02e9, B:16:0x007a] A[DONT_GENERATE, DONT_INLINE]
      0x02ec: PHI (r12v18 com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel) = 
      (r12v13 com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel)
      (r12v21 com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel)
     binds: [B:81:0x02e9, B:16:0x007a] A[DONT_GENERATE, DONT_INLINE]
      0x02ec: PHI (r13v18 java.lang.String) = (r13v13 java.lang.String), (r13v21 java.lang.String) binds: [B:81:0x02e9, B:16:0x007a] A[DONT_GENERATE, DONT_INLINE]
      0x02ec: PHI (r14v15 android.content.Context) = (r14v11 android.content.Context), (r14v18 android.content.Context) binds: [B:81:0x02e9, B:16:0x007a] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {all -> 0x0349, blocks: (B:13:0x0055, B:86:0x0328, B:16:0x007a, B:83:0x02ec, B:19:0x00a5, B:78:0x0285, B:80:0x0294, B:79:0x028f, B:22:0x00ca, B:58:0x01f9, B:60:0x01ff, B:70:0x022a, B:25:0x00ee, B:43:0x016d, B:45:0x018e, B:50:0x01a4, B:52:0x01ac, B:54:0x01b2, B:49:0x0198, B:28:0x010b, B:39:0x014d, B:31:0x0114, B:33:0x0123, B:35:0x0129, B:87:0x0331, B:88:0x033c, B:89:0x033d, B:90:0x0348), top: B:98:0x002a }] */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0325, code lost:
    
        if (r2.syncDocuments(r10, r3) == r4) goto L85;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object completeFirstLoginUsername(Context context, String str, boolean z, Continuation<? super Boolean> continuation) {
        C40631 c40631;
        Object objM7781constructorimpl;
        boolean z2;
        String str2;
        FirebaseUser firebaseUser;
        Context context2;
        int i;
        MasrofViewModel masrofViewModel;
        String str3;
        Object objCurrentProfile;
        MasrofViewModel masrofViewModel2;
        boolean z3;
        Map map;
        AuthenticatedUser authenticatedUser;
        Object obj;
        String string;
        Object obj2;
        String string2;
        Object objFindActiveUser;
        Context context3;
        MasrofViewModel masrofViewModel3;
        FirebaseUser firebaseUser2;
        String str4;
        Map map2;
        String str5;
        AuthenticatedUser authenticatedUser2;
        UserEntity userEntity;
        String str6;
        AuthenticatedUser authenticatedUser3;
        Map map3;
        MasrofViewModel masrofViewModel4;
        FirebaseUser firebaseUser3;
        String str7;
        MasrofRepository masrofRepository;
        int i2;
        String str8;
        UserEntity userEntityCopy$default;
        Map map4;
        String str9;
        String str10;
        Context context4;
        MasrofRepository masrofRepository2;
        AuditLogEntity auditLogEntity;
        if (continuation instanceof C40631) {
            c40631 = (C40631) continuation;
            if ((c40631.label & Integer.MIN_VALUE) != 0) {
                c40631.label -= Integer.MIN_VALUE;
            } else {
                c40631 = new C40631(continuation);
            }
        } else {
            c40631 = new C40631(continuation);
        }
        Object obj3 = c40631.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c40631.label) {
                case 0:
                    ResultKt.throwOnFailure(obj3);
                    Result.Companion companion = Result.INSTANCE;
                    MasrofViewModel masrofViewModel5 = this;
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (currentUser == null) {
                        throw new IllegalStateException("انتهت جلسة التهيئة الأولى".toString());
                    }
                    String email = currentUser.getEmail();
                    if (email == null) {
                        throw new IllegalStateException("لا يوجد بريد مرتبط بالحساب".toString());
                    }
                    FirebaseCloudSync firebaseCloudSync = this.cloud;
                    c40631.L$0 = context;
                    c40631.L$1 = str;
                    c40631.L$2 = this;
                    c40631.L$3 = email;
                    c40631.L$4 = currentUser;
                    z2 = z;
                    c40631.Z$0 = z2;
                    c40631.I$0 = 0;
                    c40631.label = 1;
                    if (firebaseCloudSync.bindUsername(str, email, str, c40631) != coroutine_suspended) {
                        str2 = str;
                        firebaseUser = currentUser;
                        context2 = context;
                        i = 0;
                        masrofViewModel = this;
                        str3 = email;
                        FirebaseCloudSync firebaseCloudSync2 = masrofViewModel.cloud;
                        c40631.L$0 = context2;
                        c40631.L$1 = str2;
                        c40631.L$2 = masrofViewModel;
                        c40631.L$3 = str3;
                        c40631.L$4 = firebaseUser;
                        c40631.Z$0 = z2;
                        c40631.I$0 = i;
                        c40631.label = 2;
                        objCurrentProfile = firebaseCloudSync2.currentProfile(c40631);
                        if (objCurrentProfile == coroutine_suspended) {
                            boolean z4 = z2;
                            masrofViewModel2 = masrofViewModel;
                            obj3 = objCurrentProfile;
                            z3 = z4;
                            map = (Map) obj3;
                            long jHashCode = firebaseUser.getUid().hashCode();
                            String string3 = StringsKt.trim((CharSequence) str2).toString();
                            obj = map.get("fullName");
                            if (obj != null || (string = obj.toString()) == null) {
                                string = StringsKt.trim((CharSequence) str2).toString();
                            }
                            String str11 = string;
                            obj2 = map.get("role");
                            if (obj2 != null) {
                                string2 = obj2.toString();
                            } else {
                                string2 = null;
                            }
                            authenticatedUser = new AuthenticatedUser(jHashCode, string3, str11, masrofViewModel2.roleFrom(string2));
                            UserSession.INSTANCE.setCurrent(authenticatedUser);
                            MasrofRepository masrofRepository3 = masrofViewModel2.repository;
                            c40631.L$0 = context2;
                            c40631.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                            c40631.L$2 = masrofViewModel2;
                            c40631.L$3 = SpillingKt.nullOutSpilledVariable(str3);
                            c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser);
                            c40631.L$5 = SpillingKt.nullOutSpilledVariable(map);
                            c40631.L$6 = authenticatedUser;
                            c40631.Z$0 = z3;
                            c40631.I$0 = i;
                            c40631.label = 3;
                            objFindActiveUser = masrofRepository3.findActiveUser(str3, c40631);
                            if (objFindActiveUser != coroutine_suspended) {
                                context3 = context2;
                                masrofViewModel3 = masrofViewModel2;
                                firebaseUser2 = firebaseUser;
                                str4 = str2;
                                map2 = map;
                                obj3 = objFindActiveUser;
                                str5 = str3;
                                authenticatedUser2 = authenticatedUser;
                                userEntity = (UserEntity) obj3;
                                if (userEntity != null) {
                                    MasrofViewModel masrofViewModel6 = masrofViewModel3;
                                    str6 = str4;
                                    authenticatedUser3 = authenticatedUser2;
                                    map3 = map2;
                                    masrofViewModel4 = masrofViewModel6;
                                    firebaseUser3 = firebaseUser2;
                                    str7 = str5;
                                    context4 = context3;
                                    if (z3) {
                                        RememberedLogin.INSTANCE.save(context4, authenticatedUser3.getUsername());
                                    } else {
                                        RememberedLogin.INSTANCE.clear(context4);
                                    }
                                    masrofRepository2 = masrofViewModel4.repository;
                                    auditLogEntity = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "SET_FIRST_USERNAME", "اختيار اسم المستخدم بعد إنشاء الحساب بالبريد", 0L, 33, null);
                                    c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                    c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                                    c40631.L$2 = masrofViewModel4;
                                    c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                                    c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                                    c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                                    c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                                    c40631.L$7 = null;
                                    c40631.Z$0 = z3;
                                    c40631.I$0 = i;
                                    c40631.label = 5;
                                    if (masrofRepository2.addAudit(auditLogEntity, c40631) != coroutine_suspended) {
                                        FirebaseCloudSync firebaseCloudSync3 = masrofViewModel4.cloud;
                                        MasrofRepository masrofRepository4 = masrofViewModel4.repository;
                                        c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                        c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                                        c40631.L$2 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                        c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                                        c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                                        c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                                        c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                                        c40631.Z$0 = z3;
                                        c40631.I$0 = i;
                                        c40631.label = 6;
                                        break;
                                    }
                                } else {
                                    masrofRepository = masrofViewModel3.repository;
                                    String username = authenticatedUser2.getUsername();
                                    String fullName = authenticatedUser2.getFullName();
                                    i2 = WhenMappings.$EnumSwitchMapping$0[authenticatedUser2.getRole().ordinal()];
                                    if (i2 != 1) {
                                        str8 = "SYSTEM_ADMIN";
                                    } else if (i2 != 2) {
                                        str8 = "FINANCE_DIRECTOR";
                                    } else if (i2 != 3) {
                                        str8 = "USER";
                                    } else {
                                        str8 = "ACCOUNTANT";
                                    }
                                    userEntityCopy$default = UserEntity.copy$default(userEntity, 0L, username, null, fullName, str8, false, 0L, Endpoint.TARGET_FIELD_NUMBER, null);
                                    c40631.L$0 = context3;
                                    c40631.L$1 = SpillingKt.nullOutSpilledVariable(str4);
                                    c40631.L$2 = masrofViewModel3;
                                    c40631.L$3 = SpillingKt.nullOutSpilledVariable(str5);
                                    c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser2);
                                    c40631.L$5 = SpillingKt.nullOutSpilledVariable(map2);
                                    c40631.L$6 = authenticatedUser2;
                                    c40631.L$7 = SpillingKt.nullOutSpilledVariable(userEntity);
                                    c40631.Z$0 = z3;
                                    c40631.I$0 = i;
                                    c40631.I$1 = 0;
                                    c40631.label = 4;
                                    if (masrofRepository.updateUser(userEntityCopy$default, c40631) == coroutine_suspended) {
                                        map4 = map2;
                                        str9 = str5;
                                        str10 = str4;
                                        authenticatedUser3 = authenticatedUser2;
                                        map3 = map4;
                                        firebaseUser3 = firebaseUser2;
                                        str7 = str9;
                                        masrofViewModel4 = masrofViewModel3;
                                        str6 = str10;
                                        context4 = context3;
                                        if (z3) {
                                            RememberedLogin.INSTANCE.save(context4, authenticatedUser3.getUsername());
                                        } else {
                                            RememberedLogin.INSTANCE.clear(context4);
                                        }
                                        masrofRepository2 = masrofViewModel4.repository;
                                        auditLogEntity = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "SET_FIRST_USERNAME", "اختيار اسم المستخدم بعد إنشاء الحساب بالبريد", 0L, 33, null);
                                        c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                        c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                                        c40631.L$2 = masrofViewModel4;
                                        c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                                        c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                                        c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                                        c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                                        c40631.L$7 = null;
                                        c40631.Z$0 = z3;
                                        c40631.I$0 = i;
                                        c40631.label = 5;
                                        if (masrofRepository2.addAudit(auditLogEntity, c40631) != coroutine_suspended) {
                                            FirebaseCloudSync firebaseCloudSync4 = masrofViewModel4.cloud;
                                            MasrofRepository masrofRepository5 = masrofViewModel4.repository;
                                            c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                            c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                                            c40631.L$2 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                            c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                                            c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                                            c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                                            c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                                            c40631.Z$0 = z3;
                                            c40631.I$0 = i;
                                            c40631.label = 6;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return coroutine_suspended;
                case 1:
                    i = c40631.I$0;
                    boolean z5 = c40631.Z$0;
                    firebaseUser = (FirebaseUser) c40631.L$4;
                    str3 = (String) c40631.L$3;
                    MasrofViewModel masrofViewModel7 = (MasrofViewModel) c40631.L$2;
                    str2 = (String) c40631.L$1;
                    context2 = (Context) c40631.L$0;
                    ResultKt.throwOnFailure(obj3);
                    masrofViewModel = masrofViewModel7;
                    z2 = z5;
                    FirebaseCloudSync firebaseCloudSync5 = masrofViewModel.cloud;
                    c40631.L$0 = context2;
                    c40631.L$1 = str2;
                    c40631.L$2 = masrofViewModel;
                    c40631.L$3 = str3;
                    c40631.L$4 = firebaseUser;
                    c40631.Z$0 = z2;
                    c40631.I$0 = i;
                    c40631.label = 2;
                    objCurrentProfile = firebaseCloudSync5.currentProfile(c40631);
                    if (objCurrentProfile == coroutine_suspended) {
                        boolean z6 = z2;
                        masrofViewModel2 = masrofViewModel;
                        obj3 = objCurrentProfile;
                        z3 = z6;
                        map = (Map) obj3;
                        long jHashCode2 = firebaseUser.getUid().hashCode();
                        String string4 = StringsKt.trim((CharSequence) str2).toString();
                        obj = map.get("fullName");
                        if (obj != null) {
                            string = StringsKt.trim((CharSequence) str2).toString();
                        } else {
                            string = StringsKt.trim((CharSequence) str2).toString();
                        }
                        String str12 = string;
                        obj2 = map.get("role");
                        if (obj2 != null) {
                            string2 = obj2.toString();
                        } else {
                            string2 = null;
                        }
                        authenticatedUser = new AuthenticatedUser(jHashCode2, string4, str12, masrofViewModel2.roleFrom(string2));
                        UserSession.INSTANCE.setCurrent(authenticatedUser);
                        MasrofRepository masrofRepository6 = masrofViewModel2.repository;
                        c40631.L$0 = context2;
                        c40631.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                        c40631.L$2 = masrofViewModel2;
                        c40631.L$3 = SpillingKt.nullOutSpilledVariable(str3);
                        c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser);
                        c40631.L$5 = SpillingKt.nullOutSpilledVariable(map);
                        c40631.L$6 = authenticatedUser;
                        c40631.Z$0 = z3;
                        c40631.I$0 = i;
                        c40631.label = 3;
                        objFindActiveUser = masrofRepository6.findActiveUser(str3, c40631);
                        if (objFindActiveUser != coroutine_suspended) {
                            context3 = context2;
                            masrofViewModel3 = masrofViewModel2;
                            firebaseUser2 = firebaseUser;
                            str4 = str2;
                            map2 = map;
                            obj3 = objFindActiveUser;
                            str5 = str3;
                            authenticatedUser2 = authenticatedUser;
                            userEntity = (UserEntity) obj3;
                            if (userEntity != null) {
                                MasrofViewModel masrofViewModel8 = masrofViewModel3;
                                str6 = str4;
                                authenticatedUser3 = authenticatedUser2;
                                map3 = map2;
                                masrofViewModel4 = masrofViewModel8;
                                firebaseUser3 = firebaseUser2;
                                str7 = str5;
                                context4 = context3;
                                if (z3) {
                                    RememberedLogin.INSTANCE.save(context4, authenticatedUser3.getUsername());
                                } else {
                                    RememberedLogin.INSTANCE.clear(context4);
                                }
                                masrofRepository2 = masrofViewModel4.repository;
                                auditLogEntity = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "SET_FIRST_USERNAME", "اختيار اسم المستخدم بعد إنشاء الحساب بالبريد", 0L, 33, null);
                                c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                                c40631.L$2 = masrofViewModel4;
                                c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                                c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                                c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                                c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                                c40631.L$7 = null;
                                c40631.Z$0 = z3;
                                c40631.I$0 = i;
                                c40631.label = 5;
                                if (masrofRepository2.addAudit(auditLogEntity, c40631) != coroutine_suspended) {
                                    FirebaseCloudSync firebaseCloudSync6 = masrofViewModel4.cloud;
                                    MasrofRepository masrofRepository7 = masrofViewModel4.repository;
                                    c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                    c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                                    c40631.L$2 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                    c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                                    c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                                    c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                                    c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                                    c40631.Z$0 = z3;
                                    c40631.I$0 = i;
                                    c40631.label = 6;
                                    break;
                                }
                            } else {
                                masrofRepository = masrofViewModel3.repository;
                                String username2 = authenticatedUser2.getUsername();
                                String fullName2 = authenticatedUser2.getFullName();
                                i2 = WhenMappings.$EnumSwitchMapping$0[authenticatedUser2.getRole().ordinal()];
                                if (i2 != 1) {
                                    str8 = "SYSTEM_ADMIN";
                                } else if (i2 != 2) {
                                    str8 = "FINANCE_DIRECTOR";
                                } else if (i2 != 3) {
                                    str8 = "USER";
                                } else {
                                    str8 = "ACCOUNTANT";
                                }
                                userEntityCopy$default = UserEntity.copy$default(userEntity, 0L, username2, null, fullName2, str8, false, 0L, Endpoint.TARGET_FIELD_NUMBER, null);
                                c40631.L$0 = context3;
                                c40631.L$1 = SpillingKt.nullOutSpilledVariable(str4);
                                c40631.L$2 = masrofViewModel3;
                                c40631.L$3 = SpillingKt.nullOutSpilledVariable(str5);
                                c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser2);
                                c40631.L$5 = SpillingKt.nullOutSpilledVariable(map2);
                                c40631.L$6 = authenticatedUser2;
                                c40631.L$7 = SpillingKt.nullOutSpilledVariable(userEntity);
                                c40631.Z$0 = z3;
                                c40631.I$0 = i;
                                c40631.I$1 = 0;
                                c40631.label = 4;
                                if (masrofRepository.updateUser(userEntityCopy$default, c40631) == coroutine_suspended) {
                                    map4 = map2;
                                    str9 = str5;
                                    str10 = str4;
                                    authenticatedUser3 = authenticatedUser2;
                                    map3 = map4;
                                    firebaseUser3 = firebaseUser2;
                                    str7 = str9;
                                    masrofViewModel4 = masrofViewModel3;
                                    str6 = str10;
                                    context4 = context3;
                                    if (z3) {
                                        RememberedLogin.INSTANCE.save(context4, authenticatedUser3.getUsername());
                                    } else {
                                        RememberedLogin.INSTANCE.clear(context4);
                                    }
                                    masrofRepository2 = masrofViewModel4.repository;
                                    auditLogEntity = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "SET_FIRST_USERNAME", "اختيار اسم المستخدم بعد إنشاء الحساب بالبريد", 0L, 33, null);
                                    c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                    c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                                    c40631.L$2 = masrofViewModel4;
                                    c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                                    c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                                    c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                                    c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                                    c40631.L$7 = null;
                                    c40631.Z$0 = z3;
                                    c40631.I$0 = i;
                                    c40631.label = 5;
                                    if (masrofRepository2.addAudit(auditLogEntity, c40631) != coroutine_suspended) {
                                        FirebaseCloudSync firebaseCloudSync7 = masrofViewModel4.cloud;
                                        MasrofRepository masrofRepository8 = masrofViewModel4.repository;
                                        c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                        c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                                        c40631.L$2 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                        c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                                        c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                                        c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                                        c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                                        c40631.Z$0 = z3;
                                        c40631.I$0 = i;
                                        c40631.label = 6;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    return coroutine_suspended;
                case 2:
                    i = c40631.I$0;
                    z3 = c40631.Z$0;
                    firebaseUser = (FirebaseUser) c40631.L$4;
                    str3 = (String) c40631.L$3;
                    masrofViewModel2 = (MasrofViewModel) c40631.L$2;
                    str2 = (String) c40631.L$1;
                    context2 = (Context) c40631.L$0;
                    ResultKt.throwOnFailure(obj3);
                    map = (Map) obj3;
                    long jHashCode3 = firebaseUser.getUid().hashCode();
                    String string5 = StringsKt.trim((CharSequence) str2).toString();
                    obj = map.get("fullName");
                    if (obj != null) {
                        string = StringsKt.trim((CharSequence) str2).toString();
                    } else {
                        string = StringsKt.trim((CharSequence) str2).toString();
                    }
                    String str13 = string;
                    obj2 = map.get("role");
                    if (obj2 != null) {
                        string2 = obj2.toString();
                    } else {
                        string2 = null;
                    }
                    authenticatedUser = new AuthenticatedUser(jHashCode3, string5, str13, masrofViewModel2.roleFrom(string2));
                    UserSession.INSTANCE.setCurrent(authenticatedUser);
                    MasrofRepository masrofRepository9 = masrofViewModel2.repository;
                    c40631.L$0 = context2;
                    c40631.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                    c40631.L$2 = masrofViewModel2;
                    c40631.L$3 = SpillingKt.nullOutSpilledVariable(str3);
                    c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser);
                    c40631.L$5 = SpillingKt.nullOutSpilledVariable(map);
                    c40631.L$6 = authenticatedUser;
                    c40631.Z$0 = z3;
                    c40631.I$0 = i;
                    c40631.label = 3;
                    objFindActiveUser = masrofRepository9.findActiveUser(str3, c40631);
                    if (objFindActiveUser != coroutine_suspended) {
                        context3 = context2;
                        masrofViewModel3 = masrofViewModel2;
                        firebaseUser2 = firebaseUser;
                        str4 = str2;
                        map2 = map;
                        obj3 = objFindActiveUser;
                        str5 = str3;
                        authenticatedUser2 = authenticatedUser;
                        userEntity = (UserEntity) obj3;
                        if (userEntity != null) {
                            MasrofViewModel masrofViewModel9 = masrofViewModel3;
                            str6 = str4;
                            authenticatedUser3 = authenticatedUser2;
                            map3 = map2;
                            masrofViewModel4 = masrofViewModel9;
                            firebaseUser3 = firebaseUser2;
                            str7 = str5;
                            context4 = context3;
                            if (z3) {
                                RememberedLogin.INSTANCE.save(context4, authenticatedUser3.getUsername());
                            } else {
                                RememberedLogin.INSTANCE.clear(context4);
                            }
                            masrofRepository2 = masrofViewModel4.repository;
                            auditLogEntity = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "SET_FIRST_USERNAME", "اختيار اسم المستخدم بعد إنشاء الحساب بالبريد", 0L, 33, null);
                            c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                            c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                            c40631.L$2 = masrofViewModel4;
                            c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                            c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                            c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                            c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                            c40631.L$7 = null;
                            c40631.Z$0 = z3;
                            c40631.I$0 = i;
                            c40631.label = 5;
                            if (masrofRepository2.addAudit(auditLogEntity, c40631) != coroutine_suspended) {
                                FirebaseCloudSync firebaseCloudSync8 = masrofViewModel4.cloud;
                                MasrofRepository masrofRepository10 = masrofViewModel4.repository;
                                c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                                c40631.L$2 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                                c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                                c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                                c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                                c40631.Z$0 = z3;
                                c40631.I$0 = i;
                                c40631.label = 6;
                                break;
                            }
                        } else {
                            masrofRepository = masrofViewModel3.repository;
                            String username3 = authenticatedUser2.getUsername();
                            String fullName3 = authenticatedUser2.getFullName();
                            i2 = WhenMappings.$EnumSwitchMapping$0[authenticatedUser2.getRole().ordinal()];
                            if (i2 != 1) {
                                str8 = "SYSTEM_ADMIN";
                            } else if (i2 != 2) {
                                str8 = "FINANCE_DIRECTOR";
                            } else if (i2 != 3) {
                                str8 = "USER";
                            } else {
                                str8 = "ACCOUNTANT";
                            }
                            userEntityCopy$default = UserEntity.copy$default(userEntity, 0L, username3, null, fullName3, str8, false, 0L, Endpoint.TARGET_FIELD_NUMBER, null);
                            c40631.L$0 = context3;
                            c40631.L$1 = SpillingKt.nullOutSpilledVariable(str4);
                            c40631.L$2 = masrofViewModel3;
                            c40631.L$3 = SpillingKt.nullOutSpilledVariable(str5);
                            c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser2);
                            c40631.L$5 = SpillingKt.nullOutSpilledVariable(map2);
                            c40631.L$6 = authenticatedUser2;
                            c40631.L$7 = SpillingKt.nullOutSpilledVariable(userEntity);
                            c40631.Z$0 = z3;
                            c40631.I$0 = i;
                            c40631.I$1 = 0;
                            c40631.label = 4;
                            if (masrofRepository.updateUser(userEntityCopy$default, c40631) == coroutine_suspended) {
                                map4 = map2;
                                str9 = str5;
                                str10 = str4;
                                authenticatedUser3 = authenticatedUser2;
                                map3 = map4;
                                firebaseUser3 = firebaseUser2;
                                str7 = str9;
                                masrofViewModel4 = masrofViewModel3;
                                str6 = str10;
                                context4 = context3;
                                if (z3) {
                                    RememberedLogin.INSTANCE.save(context4, authenticatedUser3.getUsername());
                                } else {
                                    RememberedLogin.INSTANCE.clear(context4);
                                }
                                masrofRepository2 = masrofViewModel4.repository;
                                auditLogEntity = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "SET_FIRST_USERNAME", "اختيار اسم المستخدم بعد إنشاء الحساب بالبريد", 0L, 33, null);
                                c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                                c40631.L$2 = masrofViewModel4;
                                c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                                c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                                c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                                c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                                c40631.L$7 = null;
                                c40631.Z$0 = z3;
                                c40631.I$0 = i;
                                c40631.label = 5;
                                if (masrofRepository2.addAudit(auditLogEntity, c40631) != coroutine_suspended) {
                                    FirebaseCloudSync firebaseCloudSync9 = masrofViewModel4.cloud;
                                    MasrofRepository masrofRepository11 = masrofViewModel4.repository;
                                    c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                    c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                                    c40631.L$2 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                    c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                                    c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                                    c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                                    c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                                    c40631.Z$0 = z3;
                                    c40631.I$0 = i;
                                    c40631.label = 6;
                                    break;
                                }
                            }
                        }
                    }
                    return coroutine_suspended;
                case 3:
                    i = c40631.I$0;
                    z3 = c40631.Z$0;
                    AuthenticatedUser authenticatedUser4 = (AuthenticatedUser) c40631.L$6;
                    map2 = (Map) c40631.L$5;
                    FirebaseUser firebaseUser4 = (FirebaseUser) c40631.L$4;
                    str5 = (String) c40631.L$3;
                    MasrofViewModel masrofViewModel10 = (MasrofViewModel) c40631.L$2;
                    str4 = (String) c40631.L$1;
                    Context context5 = (Context) c40631.L$0;
                    ResultKt.throwOnFailure(obj3);
                    context3 = context5;
                    authenticatedUser2 = authenticatedUser4;
                    firebaseUser2 = firebaseUser4;
                    masrofViewModel3 = masrofViewModel10;
                    userEntity = (UserEntity) obj3;
                    if (userEntity != null) {
                        MasrofViewModel masrofViewModel11 = masrofViewModel3;
                        str6 = str4;
                        authenticatedUser3 = authenticatedUser2;
                        map3 = map2;
                        masrofViewModel4 = masrofViewModel11;
                        firebaseUser3 = firebaseUser2;
                        str7 = str5;
                        context4 = context3;
                        if (z3) {
                            RememberedLogin.INSTANCE.save(context4, authenticatedUser3.getUsername());
                        } else {
                            RememberedLogin.INSTANCE.clear(context4);
                        }
                        masrofRepository2 = masrofViewModel4.repository;
                        auditLogEntity = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "SET_FIRST_USERNAME", "اختيار اسم المستخدم بعد إنشاء الحساب بالبريد", 0L, 33, null);
                        c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                        c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                        c40631.L$2 = masrofViewModel4;
                        c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                        c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                        c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                        c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                        c40631.L$7 = null;
                        c40631.Z$0 = z3;
                        c40631.I$0 = i;
                        c40631.label = 5;
                        if (masrofRepository2.addAudit(auditLogEntity, c40631) != coroutine_suspended) {
                            FirebaseCloudSync firebaseCloudSync10 = masrofViewModel4.cloud;
                            MasrofRepository masrofRepository12 = masrofViewModel4.repository;
                            c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                            c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                            c40631.L$2 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                            c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                            c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                            c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                            c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                            c40631.Z$0 = z3;
                            c40631.I$0 = i;
                            c40631.label = 6;
                            break;
                        }
                    } else {
                        masrofRepository = masrofViewModel3.repository;
                        String username4 = authenticatedUser2.getUsername();
                        String fullName4 = authenticatedUser2.getFullName();
                        i2 = WhenMappings.$EnumSwitchMapping$0[authenticatedUser2.getRole().ordinal()];
                        if (i2 != 1) {
                            str8 = "SYSTEM_ADMIN";
                        } else if (i2 != 2) {
                            str8 = "FINANCE_DIRECTOR";
                        } else if (i2 != 3) {
                            str8 = "USER";
                        } else {
                            str8 = "ACCOUNTANT";
                        }
                        userEntityCopy$default = UserEntity.copy$default(userEntity, 0L, username4, null, fullName4, str8, false, 0L, Endpoint.TARGET_FIELD_NUMBER, null);
                        c40631.L$0 = context3;
                        c40631.L$1 = SpillingKt.nullOutSpilledVariable(str4);
                        c40631.L$2 = masrofViewModel3;
                        c40631.L$3 = SpillingKt.nullOutSpilledVariable(str5);
                        c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser2);
                        c40631.L$5 = SpillingKt.nullOutSpilledVariable(map2);
                        c40631.L$6 = authenticatedUser2;
                        c40631.L$7 = SpillingKt.nullOutSpilledVariable(userEntity);
                        c40631.Z$0 = z3;
                        c40631.I$0 = i;
                        c40631.I$1 = 0;
                        c40631.label = 4;
                        if (masrofRepository.updateUser(userEntityCopy$default, c40631) == coroutine_suspended) {
                            map4 = map2;
                            str9 = str5;
                            str10 = str4;
                            authenticatedUser3 = authenticatedUser2;
                            map3 = map4;
                            firebaseUser3 = firebaseUser2;
                            str7 = str9;
                            masrofViewModel4 = masrofViewModel3;
                            str6 = str10;
                            context4 = context3;
                            if (z3) {
                                RememberedLogin.INSTANCE.save(context4, authenticatedUser3.getUsername());
                            } else {
                                RememberedLogin.INSTANCE.clear(context4);
                            }
                            masrofRepository2 = masrofViewModel4.repository;
                            auditLogEntity = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "SET_FIRST_USERNAME", "اختيار اسم المستخدم بعد إنشاء الحساب بالبريد", 0L, 33, null);
                            c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                            c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                            c40631.L$2 = masrofViewModel4;
                            c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                            c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                            c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                            c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                            c40631.L$7 = null;
                            c40631.Z$0 = z3;
                            c40631.I$0 = i;
                            c40631.label = 5;
                            if (masrofRepository2.addAudit(auditLogEntity, c40631) != coroutine_suspended) {
                                FirebaseCloudSync firebaseCloudSync11 = masrofViewModel4.cloud;
                                MasrofRepository masrofRepository13 = masrofViewModel4.repository;
                                c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                                c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                                c40631.L$2 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                                c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                                c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                                c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                                c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                                c40631.Z$0 = z3;
                                c40631.I$0 = i;
                                c40631.label = 6;
                                break;
                            }
                        }
                    }
                    return coroutine_suspended;
                case 4:
                    int i3 = c40631.I$1;
                    i = c40631.I$0;
                    z3 = c40631.Z$0;
                    authenticatedUser2 = (AuthenticatedUser) c40631.L$6;
                    map4 = (Map) c40631.L$5;
                    firebaseUser2 = (FirebaseUser) c40631.L$4;
                    str9 = (String) c40631.L$3;
                    masrofViewModel3 = (MasrofViewModel) c40631.L$2;
                    str10 = (String) c40631.L$1;
                    context3 = (Context) c40631.L$0;
                    ResultKt.throwOnFailure(obj3);
                    authenticatedUser3 = authenticatedUser2;
                    map3 = map4;
                    firebaseUser3 = firebaseUser2;
                    str7 = str9;
                    masrofViewModel4 = masrofViewModel3;
                    str6 = str10;
                    context4 = context3;
                    if (z3) {
                        RememberedLogin.INSTANCE.save(context4, authenticatedUser3.getUsername());
                    } else {
                        RememberedLogin.INSTANCE.clear(context4);
                    }
                    masrofRepository2 = masrofViewModel4.repository;
                    auditLogEntity = new AuditLogEntity(0L, Boxing.boxLong(authenticatedUser3.getId()), authenticatedUser3.getUsername(), "SET_FIRST_USERNAME", "اختيار اسم المستخدم بعد إنشاء الحساب بالبريد", 0L, 33, null);
                    c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                    c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                    c40631.L$2 = masrofViewModel4;
                    c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                    c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                    c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                    c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                    c40631.L$7 = null;
                    c40631.Z$0 = z3;
                    c40631.I$0 = i;
                    c40631.label = 5;
                    if (masrofRepository2.addAudit(auditLogEntity, c40631) != coroutine_suspended) {
                        FirebaseCloudSync firebaseCloudSync12 = masrofViewModel4.cloud;
                        MasrofRepository masrofRepository14 = masrofViewModel4.repository;
                        c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                        c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                        c40631.L$2 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                        c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                        c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                        c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                        c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                        c40631.Z$0 = z3;
                        c40631.I$0 = i;
                        c40631.label = 6;
                        break;
                    }
                    return coroutine_suspended;
                case 5:
                    i = c40631.I$0;
                    z3 = c40631.Z$0;
                    authenticatedUser3 = (AuthenticatedUser) c40631.L$6;
                    map3 = (Map) c40631.L$5;
                    firebaseUser3 = (FirebaseUser) c40631.L$4;
                    str7 = (String) c40631.L$3;
                    masrofViewModel4 = (MasrofViewModel) c40631.L$2;
                    str6 = (String) c40631.L$1;
                    context4 = (Context) c40631.L$0;
                    ResultKt.throwOnFailure(obj3);
                    FirebaseCloudSync firebaseCloudSync13 = masrofViewModel4.cloud;
                    MasrofRepository masrofRepository15 = masrofViewModel4.repository;
                    c40631.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                    c40631.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                    c40631.L$2 = SpillingKt.nullOutSpilledVariable(masrofViewModel4);
                    c40631.L$3 = SpillingKt.nullOutSpilledVariable(str7);
                    c40631.L$4 = SpillingKt.nullOutSpilledVariable(firebaseUser3);
                    c40631.L$5 = SpillingKt.nullOutSpilledVariable(map3);
                    c40631.L$6 = SpillingKt.nullOutSpilledVariable(authenticatedUser3);
                    c40631.Z$0 = z3;
                    c40631.I$0 = i;
                    c40631.label = 6;
                    break;
                case 6:
                    int i4 = c40631.I$0;
                    boolean z7 = c40631.Z$0;
                    ResultKt.throwOnFailure(obj3);
                    objM7781constructorimpl = Result.m7781constructorimpl(Boxing.boxBoolean(true));
                    Throwable thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                    if (thM7784exceptionOrNullimpl == null) {
                        return objM7781constructorimpl;
                    }
                    this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
                    return Boxing.boxBoolean(false);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00ec, code lost:
    
        if (r2.addAudit(r13, r3) == r4) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object registerUser(String str, String str2, String str3, String str4, Continuation<? super Boolean> continuation) {
        C40761 c40761;
        Object objM7781constructorimpl;
        String str5;
        String str6;
        int i;
        String str7;
        String str8;
        MasrofViewModel masrofViewModel;
        if (continuation instanceof C40761) {
            c40761 = (C40761) continuation;
            if ((c40761.label & Integer.MIN_VALUE) != 0) {
                c40761.label -= Integer.MIN_VALUE;
            } else {
                c40761 = new C40761(continuation);
            }
        } else {
            c40761 = new C40761(continuation);
        }
        Object obj = c40761.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c40761.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.lastAuthError = null;
                Result.Companion companion = Result.INSTANCE;
                MasrofViewModel masrofViewModel2 = this;
                FirebaseCloudSync firebaseCloudSync = this.cloud;
                c40761.L$0 = str;
                c40761.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                c40761.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                c40761.L$3 = SpillingKt.nullOutSpilledVariable(str4);
                c40761.L$4 = this;
                c40761.I$0 = 0;
                c40761.label = 1;
                if (firebaseCloudSync.register(str, str2, str3, c40761) != coroutine_suspended) {
                    str5 = str;
                    str6 = str2;
                    i = 0;
                    str7 = str3;
                    str8 = str4;
                    masrofViewModel = this;
                }
                return coroutine_suspended;
            }
            if (i2 == 1) {
                i = c40761.I$0;
                masrofViewModel = (MasrofViewModel) c40761.L$4;
                str8 = (String) c40761.L$3;
                str7 = (String) c40761.L$2;
                str6 = (String) c40761.L$1;
                str5 = (String) c40761.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i3 = c40761.I$0;
                ResultKt.throwOnFailure(obj);
            }
            objM7781constructorimpl = Result.m7781constructorimpl(Boxing.boxBoolean(true));
            Throwable thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
            if (thM7784exceptionOrNullimpl == null) {
                return objM7781constructorimpl;
            }
            this.lastAuthError = FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl);
            return Boxing.boxBoolean(false);
            MasrofRepository masrofRepository = masrofViewModel.repository;
            AuditLogEntity auditLogEntity = new AuditLogEntity(0L, null, StringsKt.trim((CharSequence) str5).toString(), "REGISTER_USER", "إنشاء حساب Firebase جديد", 0L, 33, null);
            c40761.L$0 = SpillingKt.nullOutSpilledVariable(str5);
            c40761.L$1 = SpillingKt.nullOutSpilledVariable(str6);
            c40761.L$2 = SpillingKt.nullOutSpilledVariable(str7);
            c40761.L$3 = SpillingKt.nullOutSpilledVariable(str8);
            c40761.L$4 = SpillingKt.nullOutSpilledVariable(masrofViewModel);
            c40761.I$0 = i;
            c40761.label = 2;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
    }

    private final AppRole roleFrom(String value) {
        if (value != null) {
            int iHashCode = value.hashCode();
            if (iHashCode != -1423952609) {
                if (iHashCode != -318238566) {
                    if (iHashCode == 415771249 && value.equals("FINANCE_DIRECTOR")) {
                        return AppRole.FINANCE_MANAGER;
                    }
                } else if (value.equals("ACCOUNTANT")) {
                    return AppRole.ACCOUNTANT;
                }
            } else if (value.equals("SYSTEM_ADMIN")) {
                return AppRole.ADMIN;
            }
        }
        return AppRole.USER;
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$createUser$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$createUser$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {259, 259}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40641 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $fullName;
        final /* synthetic */ String $password;
        final /* synthetic */ String $role;
        final /* synthetic */ String $username;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40641(String str, String str2, String str3, String str4, Continuation<? super C40641> continuation) {
            super(2, continuation);
            this.$username = str;
            this.$password = str2;
            this.$fullName = str3;
            this.$role = str4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40641(this.$username, this.$password, this.$fullName, this.$role, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40641) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x006a, code lost:
        
            if (r17.this$0.audit("CREATE_USER", r17.$username, r17) == r1) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MasrofViewModel.this.repository.insertUser(new UserEntity(0L, StringsKt.trim((CharSequence) this.$username).toString(), AuthSecurity.INSTANCE.hash(this.$password), this.$fullName, this.$role, false, 0L, 97, null), this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
            this.label = 2;
        }
    }

    public final void createUser(String username, String password, String fullName, String role) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(role, "role");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40641(username, password, fullName, role, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$updateUser$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$updateUser$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {262, 262}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $active;
        final /* synthetic */ String $fullName;
        final /* synthetic */ String $password;
        final /* synthetic */ String $role;
        final /* synthetic */ UserEntity $user;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40851(UserEntity userEntity, String str, String str2, String str3, boolean z, Continuation<? super C40851> continuation) {
            super(2, continuation);
            this.$user = userEntity;
            this.$password = str;
            this.$fullName = str2;
            this.$role = str3;
            this.$active = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40851(this.$user, this.$password, this.$fullName, this.$role, this.$active, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:19:0x0044  */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
        
            if (r17.this$0.audit("UPDATE_USER", r17.$user.getUsername(), r17) == r1) goto L25;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            String passwordHash;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                } else {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            MasrofRepository masrofRepository = MasrofViewModel.this.repository;
            UserEntity userEntity = this.$user;
            String str = this.$password;
            if (str == null) {
                passwordHash = this.$user.getPasswordHash();
            } else {
                if (StringsKt.isBlank(str)) {
                    str = null;
                }
                if (str == null || (passwordHash = AuthSecurity.INSTANCE.hash(str)) == null) {
                    passwordHash = this.$user.getPasswordHash();
                }
            }
            this.label = 1;
            if (masrofRepository.updateUser(UserEntity.copy$default(userEntity, 0L, null, passwordHash, this.$fullName, this.$role, this.$active, 0L, 67, null), this) != coroutine_suspended) {
            }
            return coroutine_suspended;
            this.label = 2;
        }
    }

    public final void updateUser(UserEntity user, String password, String fullName, String role, boolean active) {
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(role, "role");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40851(user, password, fullName, role, active, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$deleteUser$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$deleteUser$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {264, 264}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ UserEntity $user;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40671(UserEntity userEntity, Continuation<? super C40671> continuation) {
            super(2, continuation);
            this.$user = userEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40671(this.$user, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0048, code lost:
        
            if (r5.this$0.audit("DELETE_USER", r5.$user.getUsername(), r5) == r0) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MasrofViewModel.this.repository.deleteUser(this.$user, this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
            this.label = 2;
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$recordAudit$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$recordAudit$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {265}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $action;
        final /* synthetic */ String $details;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40741(String str, String str2, Continuation<? super C40741> continuation) {
            super(2, continuation);
            this.$action = str;
            this.$details = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40741(this.$action, this.$details, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MasrofViewModel.this.audit(this.$action, this.$details, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void deleteUser(UserEntity user) {
        Intrinsics.checkNotNullParameter(user, "user");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40671(user, null), 2, null);
    }

    public final void recordAudit(String action, String details) {
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(details, "details");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40741(action, details, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object audit(String str, String str2, Continuation<? super Unit> continuation) {
        Object objAddAudit;
        AuthenticatedUser current = UserSession.INSTANCE.getCurrent();
        return (current == null || (objAddAudit = this.repository.addAudit(new AuditLogEntity(0L, Boxing.boxLong(current.getId()), current.getUsername(), str, str2, 0L, 33, null), continuation)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objAddAudit;
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$addDocument$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$addDocument$1", m938f = "MasrofViewModel.kt", m939i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2}, m940l = {272, 273, 274}, m941m = "invokeSuspend", m942n = {"$this$launch", "submittedBy", "syncedDocument", "$this$launch", "submittedBy", "syncedDocument", "$this$invokeSuspend_u24lambda_u241", "$i$a$-runCatching-MasrofViewModel$addDocument$1$1", "$this$launch", "submittedBy", "syncedDocument"}, m943s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2"})
    static final class C40571 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Document $document;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ MasrofViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40571(Document document, MasrofViewModel masrofViewModel, Continuation<? super C40571> continuation) {
            super(2, continuation);
            this.$document = document;
            this.this$0 = masrofViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C40571 c40571 = new C40571(this.$document, this.this$0, continuation);
            c40571.L$0 = obj;
            return c40571;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40571) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x0160, code lost:
        
            if (r0.audit("CREATE_DOCUMENT", r8 + " — الحالة: " + r9, r43) == r3) goto L40;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            String str;
            Document documentCopy$default;
            Document document;
            MasrofViewModel masrofViewModel;
            Document document2;
            String str2;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    String submittedBy = this.$document.getSubmittedBy();
                    if (StringsKt.isBlank(submittedBy)) {
                        AuthenticatedUser current = UserSession.INSTANCE.getCurrent();
                        submittedBy = current != null ? current.getFullName() : null;
                        if (submittedBy == null) {
                            submittedBy = "";
                        }
                    }
                    str = submittedBy;
                    documentCopy$default = Document.copy$default(this.$document, 0L, null, null, null, null, null, null, null, null, null, null, null, 0, 0L, false, null, 0L, null, null, null, null, null, str, null, null, null, null, null, null, 532676607, null);
                    this.L$0 = coroutineScope;
                    this.L$1 = SpillingKt.nullOutSpilledVariable(str);
                    this.L$2 = documentCopy$default;
                    this.label = 1;
                    if (this.this$0.repository.insert(documentCopy$default, this) != coroutine_suspended) {
                    }
                    return coroutine_suspended;
                }
                if (i == 1) {
                    documentCopy$default = (Document) this.L$2;
                    String str3 = (String) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    str = str3;
                } else if (i == 2) {
                    document2 = (Document) this.L$2;
                    str2 = (String) this.L$1;
                    try {
                        ResultKt.throwOnFailure(obj);
                        Result.m7781constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion = Result.INSTANCE;
                        Result.m7781constructorimpl(ResultKt.createFailure(th));
                    }
                    MasrofViewModel masrofViewModel2 = this.this$0;
                    String documentNumber = this.$document.getDocumentNumber();
                    String strName = this.$document.getStatus().name();
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                    this.L$2 = SpillingKt.nullOutSpilledVariable(document2);
                    this.L$3 = null;
                    this.label = 3;
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
                Result.Companion companion2 = Result.INSTANCE;
                FirebaseCloudSync firebaseCloudSync = masrofViewModel.cloud;
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(str);
                this.L$2 = SpillingKt.nullOutSpilledVariable(document);
                this.L$3 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.I$0 = 0;
                this.label = 2;
                if (firebaseCloudSync.saveDocument(document, this) != coroutine_suspended) {
                    document2 = document;
                    str2 = str;
                    Result.m7781constructorimpl(Unit.INSTANCE);
                    MasrofViewModel masrofViewModel3 = this.this$0;
                    String documentNumber2 = this.$document.getDocumentNumber();
                    String strName2 = this.$document.getStatus().name();
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                    this.L$2 = SpillingKt.nullOutSpilledVariable(document2);
                    this.L$3 = null;
                    this.label = 3;
                }
            } catch (Throwable th2) {
                th = th2;
                document2 = document;
                str2 = str;
                Result.Companion companion3 = Result.INSTANCE;
                Result.m7781constructorimpl(ResultKt.createFailure(th));
            }
            document = documentCopy$default;
            masrofViewModel = this.this$0;
            return coroutine_suspended;
        }
    }

    public final void addDocument(Document document) {
        Intrinsics.checkNotNullParameter(document, "document");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C40571(document, this, null), 3, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$transitionDocument$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$transitionDocument$1", m938f = "MasrofViewModel.kt", m939i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3}, m940l = {292, 304, 305, 306}, m941m = "invokeSuspend", m942n = {"$this$launch", "role", "canApprove", "canReceive", "allowed", "$this$launch", "role", "actor", "updated", "canApprove", "canReceive", "allowed", "now", "$this$launch", "role", "actor", "updated", "$this$invokeSuspend_u24lambda_u240", "canApprove", "canReceive", "allowed", "now", "$i$a$-runCatching-MasrofViewModel$transitionDocument$1$1", "$this$launch", "role", "actor", "updated", "canApprove", "canReceive", "allowed", "now"}, m943s = {"L$0", "L$1", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1", "I$2", "J$0", "I$3", "L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2", "J$0"})
    static final class C40801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Document $document;
        final /* synthetic */ DocumentStatus $target;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        final /* synthetic */ MasrofViewModel this$0;

        /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$transitionDocument$1$WhenMappings */
        /* JADX INFO: compiled from: MasrofViewModel.kt */
        @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[DocumentStatus.values().length];
                try {
                    iArr[DocumentStatus.APPROVED_FINANCE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[DocumentStatus.APPROVED_BRANCH.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[DocumentStatus.APPROVED.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[DocumentStatus.PAID.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[DocumentStatus.CANCELLED.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[DocumentStatus.RECEIVED.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40801(DocumentStatus documentStatus, Document document, MasrofViewModel masrofViewModel, Continuation<? super C40801> continuation) {
            super(2, continuation);
            this.$target = documentStatus;
            this.$document = document;
            this.this$0 = masrofViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C40801 c40801 = new C40801(this.$target, this.$document, this.this$0, continuation);
            c40801.L$0 = obj;
            return c40801;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:112:0x02ad  */
        /* JADX WARN: Code duplicated, block: B:60:0x0123  */
        /* JADX WARN: Code restructure failed: missing block: B:119:0x0324, code lost:
        
            if (r0.audit(r4, r5 + " — بواسطة " + r12, r50) == r3) goto L120;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x017a, code lost:
        
            if (r4.audit("WORKFLOW_DENIED", r5 + " → " + r6, r50) == r3) goto L120;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            AppRole role;
            int i;
            int i2;
            String str;
            int i3;
            Document document;
            long j;
            AppRole appRole;
            int i4;
            String str2;
            AppRole appRole2;
            FirebaseCloudSync firebaseCloudSync;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i5 = this.label;
            if (i5 != 0) {
                if (i5 == 1) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                if (i5 == 2) {
                    long j2 = this.J$0;
                    int i6 = this.I$2;
                    int i7 = this.I$1;
                    int i8 = this.I$0;
                    Document document2 = (Document) this.L$3;
                    str = (String) this.L$2;
                    appRole = (AppRole) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    i3 = i7;
                    j = j2;
                    i = i8;
                    document = document2;
                    i2 = i6;
                    MasrofViewModel masrofViewModel = this.this$0;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        firebaseCloudSync = masrofViewModel.cloud;
                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(appRole);
                        this.L$2 = str;
                        this.L$3 = SpillingKt.nullOutSpilledVariable(document);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                        this.I$0 = i;
                        this.I$1 = i3;
                        this.I$2 = i2;
                        this.J$0 = j;
                        this.I$3 = 0;
                        this.label = 3;
                        if (firebaseCloudSync.saveDocument(document, this) != coroutine_suspended) {
                            i4 = i2;
                            str2 = str;
                            appRole2 = appRole;
                            Result.m7781constructorimpl(Unit.INSTANCE);
                        }
                    } catch (Throwable th) {
                        th = th;
                        i4 = i2;
                        str2 = str;
                        appRole2 = appRole;
                        Result.Companion companion2 = Result.INSTANCE;
                        Result.m7781constructorimpl(ResultKt.createFailure(th));
                    }
                    return coroutine_suspended;
                }
                if (i5 == 3) {
                    j = this.J$0;
                    i4 = this.I$2;
                    i3 = this.I$1;
                    i = this.I$0;
                    document = (Document) this.L$3;
                    str2 = (String) this.L$2;
                    appRole2 = (AppRole) this.L$1;
                    try {
                        ResultKt.throwOnFailure(obj);
                        Result.m7781constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th2) {
                        th = th2;
                        Result.Companion companion3 = Result.INSTANCE;
                        Result.m7781constructorimpl(ResultKt.createFailure(th));
                    }
                } else {
                    if (i5 != 4) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            AuthenticatedUser current = UserSession.INSTANCE.getCurrent();
            if (current == null || (role = current.getRole()) == null) {
                role = AppRole.ADMIN;
            }
            i = (role == AppRole.ADMIN || role == AppRole.FINANCE_MANAGER) ? 1 : 0;
            int i9 = (i != 0 || role == AppRole.ACCOUNTANT) ? 1 : 0;
            switch (WhenMappings.$EnumSwitchMapping$0[this.$target.ordinal()]) {
                case 1:
                    if (i != 0 && role == AppRole.FINANCE_MANAGER && this.$document.getStatus() == DocumentStatus.SUBMITTED) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    break;
                case 2:
                    if (role == AppRole.ADMIN && this.$document.getStatus() == DocumentStatus.APPROVED_FINANCE) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    break;
                case 3:
                    if (role == AppRole.ADMIN && this.$document.getStatus() == DocumentStatus.APPROVED_BRANCH) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    break;
                case 4:
                    if (i != 0 && this.$document.getStatus() == DocumentStatus.APPROVED) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    break;
                case 5:
                    if (i == 0 || SetsKt.setOf((Object[]) new DocumentStatus[]{DocumentStatus.PAID, DocumentStatus.RECEIVED, DocumentStatus.CANCELLED}).contains(this.$document.getStatus())) {
                        i2 = 0;
                    } else {
                        i2 = 1;
                    }
                    break;
                case 6:
                    if (i9 != 0 && SetsKt.setOf((Object[]) new DocumentStatus[]{DocumentStatus.APPROVED, DocumentStatus.PAID}).contains(this.$document.getStatus())) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    break;
                default:
                    i2 = 0;
                    break;
            }
            if (i2 == 0) {
                MasrofViewModel masrofViewModel2 = this.this$0;
                String documentNumber = this.$document.getDocumentNumber();
                String strName = this.$target.name();
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(role);
                this.I$0 = i;
                this.I$1 = i9;
                this.I$2 = i2;
                this.label = 1;
            } else {
                long jCurrentTimeMillis = System.currentTimeMillis();
                AuthenticatedUser current2 = UserSession.INSTANCE.getCurrent();
                String fullName = current2 != null ? current2.getFullName() : null;
                if (fullName == null) {
                    fullName = "";
                }
                Document documentCopy$default = Document.copy$default(this.$document, 0L, null, null, null, null, null, null, null, null, null, null, this.$target, 0, 0L, false, null, jCurrentTimeMillis, null, null, null, null, null, null, this.$target == DocumentStatus.APPROVED ? fullName : this.$document.getReviewedBy(), (this.$target == DocumentStatus.APPROVED || this.$target == DocumentStatus.PAID) ? fullName : this.$document.getApprovedBy(), (this.$target == DocumentStatus.APPROVED && this.$document.getApprovedAt() == null) ? Boxing.boxLong(jCurrentTimeMillis) : this.$document.getApprovedAt(), this.$target == DocumentStatus.PAID ? Boxing.boxLong(jCurrentTimeMillis) : this.$document.getPaidAt(), this.$target == DocumentStatus.CANCELLED ? "تم الإلغاء بواسطة " + fullName : this.$document.getRejectionReason(), null, 276756479, null);
                this.L$0 = coroutineScope;
                this.L$1 = SpillingKt.nullOutSpilledVariable(role);
                this.L$2 = fullName;
                this.L$3 = documentCopy$default;
                this.I$0 = i;
                this.I$1 = i9;
                this.I$2 = i2;
                this.J$0 = jCurrentTimeMillis;
                this.label = 2;
                if (this.this$0.repository.update(documentCopy$default, this) != coroutine_suspended) {
                    str = fullName;
                    i3 = i9;
                    document = documentCopy$default;
                    j = jCurrentTimeMillis;
                    appRole = role;
                    MasrofViewModel masrofViewModel3 = this.this$0;
                    Result.Companion companion4 = Result.INSTANCE;
                    firebaseCloudSync = masrofViewModel3.cloud;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(appRole);
                    this.L$2 = str;
                    this.L$3 = SpillingKt.nullOutSpilledVariable(document);
                    this.L$4 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.I$0 = i;
                    this.I$1 = i3;
                    this.I$2 = i2;
                    this.J$0 = j;
                    this.I$3 = 0;
                    this.label = 3;
                    if (firebaseCloudSync.saveDocument(document, this) != coroutine_suspended) {
                        i4 = i2;
                        str2 = str;
                        appRole2 = appRole;
                        Result.m7781constructorimpl(Unit.INSTANCE);
                    }
                }
            }
            return coroutine_suspended;
            MasrofViewModel masrofViewModel4 = this.this$0;
            String str3 = "WORKFLOW_" + this.$target.name();
            String documentNumber2 = this.$document.getDocumentNumber();
            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(appRole2);
            this.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            this.L$3 = SpillingKt.nullOutSpilledVariable(document);
            this.L$4 = null;
            this.I$0 = i;
            this.I$1 = i3;
            this.I$2 = i4;
            this.J$0 = j;
            this.label = 4;
        }
    }

    public final void transitionDocument(Document document, DocumentStatus target) {
        Intrinsics.checkNotNullParameter(document, "document");
        Intrinsics.checkNotNullParameter(target, "target");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40801(target, document, this, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$updateDocument$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$updateDocument$1", m938f = "MasrofViewModel.kt", m939i = {0, 1, 1, 1, 2}, m940l = {311, 311, 311}, m941m = "invokeSuspend", m942n = {"$this$launch", "$this$launch", "$this$invokeSuspend_u24lambda_u240", "$i$a$-runCatching-MasrofViewModel$updateDocument$1$1", "$this$launch"}, m943s = {"L$0", "L$0", "L$1", "I$0", "L$0"})
    static final class C40841 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Document $document;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40841(Document document, Continuation<? super C40841> continuation) {
            super(2, continuation);
            this.$document = document;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C40841 c40841 = MasrofViewModel.this.new C40841(this.$document, continuation);
            c40841.L$0 = obj;
            return c40841;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40841) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x0096, code lost:
        
            if (r7.this$0.audit("UPDATE_DOCUMENT", r7.$document.getDocumentNumber(), r7) == r1) goto L28;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (MasrofViewModel.this.repository.update(this.$document, this) != coroutine_suspended) {
                    }
                    return coroutine_suspended;
                }
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                } else if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    Result.m7781constructorimpl(Unit.INSTANCE);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = null;
                    this.label = 3;
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
                MasrofViewModel masrofViewModel = MasrofViewModel.this;
                Document document = this.$document;
                Result.Companion companion = Result.INSTANCE;
                FirebaseCloudSync firebaseCloudSync = masrofViewModel.cloud;
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.I$0 = 0;
                this.label = 2;
                if (firebaseCloudSync.saveDocument(document, this) != coroutine_suspended) {
                    Result.m7781constructorimpl(Unit.INSTANCE);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = null;
                    this.label = 3;
                }
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m7781constructorimpl(ResultKt.createFailure(th));
            }
            return coroutine_suspended;
        }
    }

    public final void updateDocument(Document document) {
        Intrinsics.checkNotNullParameter(document, "document");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C40841(document, null), 3, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$deleteDocument$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$deleteDocument$1", m938f = "MasrofViewModel.kt", m939i = {0, 1, 1, 1, 2}, m940l = {315, 315, 315}, m941m = "invokeSuspend", m942n = {"$this$launch", "$this$launch", "$this$invokeSuspend_u24lambda_u240", "$i$a$-runCatching-MasrofViewModel$deleteDocument$1$1", "$this$launch"}, m943s = {"L$0", "L$0", "L$1", "I$0", "L$0"})
    static final class C40661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Document $document;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40661(Document document, Continuation<? super C40661> continuation) {
            super(2, continuation);
            this.$document = document;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C40661 c40661 = MasrofViewModel.this.new C40661(this.$document, continuation);
            c40661.L$0 = obj;
            return c40661;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40661) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x00da, code lost:
        
            if (r41.this$0.audit("ARCHIVE_DOCUMENT", r41.$document.getDocumentNumber(), r41) == r3) goto L28;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (MasrofViewModel.this.repository.delete(this.$document, this) != coroutine_suspended) {
                    }
                    return coroutine_suspended;
                }
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                } else if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    Result.m7781constructorimpl(Unit.INSTANCE);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = null;
                    this.label = 3;
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
                MasrofViewModel masrofViewModel = MasrofViewModel.this;
                Document document = this.$document;
                Result.Companion companion = Result.INSTANCE;
                FirebaseCloudSync firebaseCloudSync = masrofViewModel.cloud;
                Document documentCopy$default = Document.copy$default(document, 0L, null, null, null, null, null, null, null, null, null, null, null, 0, 0L, true, null, System.currentTimeMillis(), null, null, null, null, null, null, null, null, null, null, null, null, 536788991, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.I$0 = 0;
                this.label = 2;
                if (firebaseCloudSync.saveDocument(documentCopy$default, this) != coroutine_suspended) {
                    Result.m7781constructorimpl(Unit.INSTANCE);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = null;
                    this.label = 3;
                }
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m7781constructorimpl(ResultKt.createFailure(th));
            }
            return coroutine_suspended;
        }
    }

    public final void deleteDocument(Document document) {
        Intrinsics.checkNotNullParameter(document, "document");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C40661(document, null), 3, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$archiveDocument$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$archiveDocument$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {319, 319}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Document $document;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40591(Document document, Continuation<? super C40591> continuation) {
            super(2, continuation);
            this.$document = document;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40591(this.$document, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x004c, code lost:
        
            if (r7.this$0.audit("ARCHIVE_DOCUMENT", r7.$document.getDocumentNumber(), r7) == r0) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MasrofViewModel.this.repository.archive(this.$document, System.currentTimeMillis(), this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
            this.label = 2;
        }
    }

    public final void archiveDocument(Document document) {
        Intrinsics.checkNotNullParameter(document, "document");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40591(document, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$restoreDocument$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$restoreDocument$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {323, 323}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Document $document;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40771(Document document, Continuation<? super C40771> continuation) {
            super(2, continuation);
            this.$document = document;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40771(this.$document, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x004c, code lost:
        
            if (r7.this$0.audit("RESTORE_DOCUMENT", r7.$document.getDocumentNumber(), r7) == r0) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (MasrofViewModel.this.repository.restore(this.$document, System.currentTimeMillis(), this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
            this.label = 2;
        }
    }

    public final void restoreDocument(Document document) {
        Intrinsics.checkNotNullParameter(document, "document");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40771(document, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$exportDatabase$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$exportDatabase$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40681 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Uri $uri;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40681(Context context, Uri uri, Continuation<? super C40681> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$uri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C40681(this.$context, this.$uri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40681) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws FileNotFoundException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            File databasePath = this.$context.getDatabasePath("masrof-db");
            OutputStream outputStreamOpenOutputStream = this.$context.getContentResolver().openOutputStream(this.$uri);
            if (outputStreamOpenOutputStream != null) {
                OutputStream outputStream = outputStreamOpenOutputStream;
                try {
                    OutputStream outputStream2 = outputStream;
                    FileInputStream fileInputStream = new FileInputStream(databasePath);
                    try {
                        ByteStreamsKt.copyTo$default(fileInputStream, outputStream2, 0, 2, null);
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileInputStream, null);
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(outputStream, null);
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(fileInputStream, th);
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(outputStream, th3);
                        throw th4;
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final void exportDatabase(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40681(context, uri, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$importDatabase$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$importDatabase$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40701 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Uri $uri;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40701(Context context, Uri uri, Continuation<? super C40701> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$uri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40701(this.$context, this.$uri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40701) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws FileNotFoundException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                MasrofViewModel.this.database.close();
                File databasePath = this.$context.getDatabasePath("masrof-db");
                InputStream inputStreamOpenInputStream = this.$context.getContentResolver().openInputStream(this.$uri);
                if (inputStreamOpenInputStream != null) {
                    InputStream inputStream = inputStreamOpenInputStream;
                    try {
                        InputStream inputStream2 = inputStream;
                        FileOutputStream fileOutputStream = new FileOutputStream(databasePath);
                        try {
                            ByteStreamsKt.copyTo$default(inputStream2, fileOutputStream, 0, 2, null);
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(fileOutputStream, null);
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(inputStream, null);
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                CloseableKt.closeFinally(fileOutputStream, th);
                                throw th2;
                            }
                        }
                    } catch (Throwable th3) {
                        try {
                            throw th3;
                        } catch (Throwable th4) {
                            CloseableKt.closeFinally(inputStream, th3);
                            throw th4;
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void importDatabase(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40701(context, uri, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$exportFullBackup$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$exportFullBackup$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40691 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Uri $uri;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40691(Context context, Uri uri, Continuation<? super C40691> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$uri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C40691(this.$context, this.$uri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40691) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AppBackupManager.INSTANCE.exportToUri(this.$context, this.$uri);
            return Unit.INSTANCE;
        }
    }

    public final void exportFullBackup(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40691(context, uri, null), 2, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$importFullBackup$1 */
    /* JADX INFO: compiled from: MasrofViewModel.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel$importFullBackup$1", m938f = "MasrofViewModel.kt", m939i = {}, m940l = {}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C40711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Uri $uri;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40711(Context context, Uri uri, Continuation<? super C40711> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$uri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MasrofViewModel.this.new C40711(this.$context, this.$uri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C40711) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws FileNotFoundException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                MasrofViewModel.this.database.close();
                AppBackupManager.INSTANCE.restoreFromUri(this.$context, this.$uri);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void importFullBackup(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C40711(context, uri, null), 2, null);
    }
}
