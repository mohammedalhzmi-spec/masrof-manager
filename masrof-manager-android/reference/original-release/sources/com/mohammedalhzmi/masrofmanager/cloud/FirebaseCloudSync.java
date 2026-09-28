package com.mohammedalhzmi.masrofmanager.cloud;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.core.location.LocationRequestCompat;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.messaging.Constants;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mohammedalhzmi.masrofmanager.data.Document;
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import com.mohammedalhzmi.masrofmanager.data.MasrofRepository;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.tasks.TasksKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.internal.p017ws.WebSocketProtocol;

/* JADX INFO: compiled from: FirebaseCloudSync.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001:\u0002;<B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0002J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u001e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0016J\u001e\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0016J\u001e\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0016J\u001e\u0010\u0019\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0016J*\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0005H\u0086@¢\u0006\u0002\u0010\u001cJ&\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u001cJ\u001c\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001fH\u0086@¢\u0006\u0002\u0010 J\u0006\u0010!\u001a\u00020\u000eJ\u0016\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010#J\u000e\u0010$\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010 J\u0006\u0010%\u001a\u00020&J\u0016\u0010'\u001a\u00020(2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010)J\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+H\u0086@¢\u0006\u0002\u0010 J\u0016\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020,H\u0086@¢\u0006\u0002\u0010/J\u0016\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0086@¢\u0006\u0002\u00104J\u0016\u00105\u001a\u00020\u000e2\u0006\u00106\u001a\u000207H\u0086@¢\u0006\u0002\u00108J\u000e\u00109\u001a\u0004\u0018\u000107*\u00020:H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006="}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/cloud/FirebaseCloudSync;", "", "<init>", "()V", "MANAGER_EMAIL", "", "credentialsEmail", "identifier", "usernameKey", HintConstants.AUTOFILL_HINT_USERNAME, "friendlyAuthError", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "initialize", "", "context", "Landroid/content/Context;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "signIn", "email", "password", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signInByEmail", "createFirstAccount", "signInByUsername", "bindUsername", "fullName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "currentProfile", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signOut", "sendPasswordReset", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendEmailVerification", "isEmailVerified", "", "authorizeCurrentDevice", "Lcom/mohammedalhzmi/masrofmanager/cloud/FirebaseCloudSync$AccessDecision;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pendingDeviceRequests", "", "Lcom/mohammedalhzmi/masrofmanager/cloud/FirebaseCloudSync$DeviceRequest;", "approveDevice", "request", "(Lcom/mohammedalhzmi/masrofmanager/cloud/FirebaseCloudSync$DeviceRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncDocuments", "", "repository", "Lcom/mohammedalhzmi/masrofmanager/data/MasrofRepository;", "(Lcom/mohammedalhzmi/masrofmanager/data/MasrofRepository;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDocument", "document", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "(Lcom/mohammedalhzmi/masrofmanager/data/Document;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toLocalDocument", "Lcom/google/firebase/firestore/DocumentSnapshot;", "AccessDecision", "DeviceRequest", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class FirebaseCloudSync {
    public static final int $stable = 0;
    public static final FirebaseCloudSync INSTANCE = new FirebaseCloudSync();
    public static final String MANAGER_EMAIL = "[REDACTED_EMAIL]";

    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FirebaseFirestoreException.Code.values().length];
            try {
                iArr[FirebaseFirestoreException.Code.PERMISSION_DENIED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirebaseFirestoreException.Code.UNAVAILABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$approveDevice$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {0, 0, 1, 1, 2, 2, 3, 3}, m940l = {183, 184, 185, 186}, m941m = "approveDevice", m942n = {"request", "actor", "request", "actor", "request", "actor", "request", "actor"}, m943s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
    static final class C38421 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C38421(Continuation<? super C38421> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.approveDevice(null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$authorizeCurrentDevice$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}, m940l = {137, 142, 151, 155, 158, 165, 170}, m941m = "authorizeCurrentDevice", m942n = {"context", "context", "authUser", "email", "userRef", "isManager", "context", "authUser", "email", "userRef", "existingProfile", "role", Scopes.PROFILE, "isManager", "context", "authUser", "email", "userRef", "existingProfile", "role", Scopes.PROFILE, "deviceId", "deviceRef", "isManager", "context", "authUser", "email", "userRef", "existingProfile", "role", Scopes.PROFILE, "deviceId", "deviceRef", "deviceSnapshot", "current", "$this$authorizeCurrentDevice_u24lambda_u245", "isManager", "approved", "$i$a$-runCatching-FirebaseCloudSync$authorizeCurrentDevice$token$1", "context", "authUser", "email", "userRef", "existingProfile", "role", Scopes.PROFILE, "deviceId", "deviceRef", "deviceSnapshot", "current", "token", "isManager", "approved", "context", "authUser", "email", "userRef", "existingProfile", "role", Scopes.PROFILE, "deviceId", "deviceRef", "deviceSnapshot", "current", "token", "isManager", "approved"}, m943s = {"L$0", "L$0", "L$1", "L$2", "L$3", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "Z$0", "I$0"})
    static final class C38431 extends ContinuationImpl {
        int I$0;
        int I$1;
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

        C38431(Continuation<? super C38431> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.authorizeCurrentDevice(null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$bindUsername$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, m940l = {95, 99}, m941m = "bindUsername", m942n = {HintConstants.AUTOFILL_HINT_USERNAME, "email", "fullName", "user", "key", HintConstants.AUTOFILL_HINT_USERNAME, "email", "fullName", "user", "key"}, m943s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class C38441 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C38441(Continuation<? super C38441> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.bindUsername(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$createFirstAccount$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {0, 0, 0, 1, 1, 1, 1, 1, 1}, m940l = {69, 77}, m941m = "createFirstAccount", m942n = {"email", "password", "cleanEmail", "email", "password", "cleanEmail", "result", "uid", "manager"}, m943s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0"})
    static final class C38451 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C38451(Continuation<? super C38451> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.createFirstAccount(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$currentProfile$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {0}, m940l = {116}, m941m = "currentProfile", m942n = {"uid"}, m943s = {"L$0"})
    static final class C38461 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C38461(Continuation<? super C38461> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.currentProfile(this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$pendingDeviceRequests$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {}, m940l = {177}, m941m = "pendingDeviceRequests", m942n = {}, m943s = {})
    static final class C38471 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C38471(Continuation<? super C38471> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.pendingDeviceRequests(this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$register$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, m940l = {LocationRequestCompat.QUALITY_LOW_POWER, 110}, m941m = "register", m942n = {"email", "password", "fullName", "loginEmail", "email", "password", "fullName", "loginEmail", "result", "uid"}, m943s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
    static final class C38481 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C38481(Continuation<? super C38481> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.register(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$sendEmailVerification$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {}, m940l = {130}, m941m = "sendEmailVerification", m942n = {}, m943s = {})
    static final class C38491 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C38491(Continuation<? super C38491> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.sendEmailVerification(this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$sendPasswordReset$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {0, 1, 1}, m940l = {123, WebSocketProtocol.PAYLOAD_SHORT}, m941m = "sendPasswordReset", m942n = {"identifier", "identifier", "email"}, m943s = {"L$0", "L$0", "L$1"})
    static final class C38501 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C38501(Continuation<? super C38501> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.sendPasswordReset(null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$signIn$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {0, 0}, m940l = {57}, m941m = "signIn", m942n = {"email", "password"}, m943s = {"L$0", "L$1"})
    static final class C38511 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C38511(Continuation<? super C38511> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.signIn(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$signInByEmail$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {0, 0}, m940l = {62}, m941m = "signInByEmail", m942n = {"email", "password"}, m943s = {"L$0", "L$1"})
    static final class C38521 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C38521(Continuation<? super C38521> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.signInByEmail(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$signInByUsername$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {0, 0, 1, 1, 1, 1}, m940l = {82, 84}, m941m = "signInByUsername", m942n = {HintConstants.AUTOFILL_HINT_USERNAME, "password", HintConstants.AUTOFILL_HINT_USERNAME, "password", "alias", "email"}, m943s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3"})
    static final class C38531 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C38531(Continuation<? super C38531> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.signInByUsername(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$syncDocuments$1 */
    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync", m938f = "FirebaseCloudSync.kt", m939i = {0, 1, 1, 1}, m940l = {191, 193}, m941m = "syncDocuments", m942n = {"repository", "repository", "snapshot", "documents"}, m943s = {"L$0", "L$0", "L$1", "L$2"})
    static final class C38541 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C38541(Continuation<? super C38541> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirebaseCloudSync.this.syncDocuments(null, this);
        }
    }

    private FirebaseCloudSync() {
    }

    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m913d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/cloud/FirebaseCloudSync$AccessDecision;", "", "allowed", "", "isManager", "role", "", "message", "<init>", "(ZZLjava/lang/String;Ljava/lang/String;)V", "getAllowed", "()Z", "getRole", "()Ljava/lang/String;", "getMessage", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* data */ class AccessDecision {
        public static final int $stable = 0;
        private final boolean allowed;
        private final boolean isManager;
        private final String message;
        private final String role;

        public static /* synthetic */ AccessDecision copy$default(AccessDecision accessDecision, boolean z, boolean z2, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = accessDecision.allowed;
            }
            if ((i & 2) != 0) {
                z2 = accessDecision.isManager;
            }
            if ((i & 4) != 0) {
                str = accessDecision.role;
            }
            if ((i & 8) != 0) {
                str2 = accessDecision.message;
            }
            return accessDecision.copy(z, z2, str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getAllowed() {
            return this.allowed;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsManager() {
            return this.isManager;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getRole() {
            return this.role;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final AccessDecision copy(boolean allowed, boolean isManager, String role, String message) {
            Intrinsics.checkNotNullParameter(role, "role");
            Intrinsics.checkNotNullParameter(message, "message");
            return new AccessDecision(allowed, isManager, role, message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AccessDecision)) {
                return false;
            }
            AccessDecision accessDecision = (AccessDecision) other;
            return this.allowed == accessDecision.allowed && this.isManager == accessDecision.isManager && Intrinsics.areEqual(this.role, accessDecision.role) && Intrinsics.areEqual(this.message, accessDecision.message);
        }

        public int hashCode() {
            return (((((Boolean.hashCode(this.allowed) * 31) + Boolean.hashCode(this.isManager)) * 31) + this.role.hashCode()) * 31) + this.message.hashCode();
        }

        public String toString() {
            return "AccessDecision(allowed=" + this.allowed + ", isManager=" + this.isManager + ", role=" + this.role + ", message=" + this.message + ")";
        }

        public AccessDecision(boolean z, boolean z2, String role, String message) {
            Intrinsics.checkNotNullParameter(role, "role");
            Intrinsics.checkNotNullParameter(message, "message");
            this.allowed = z;
            this.isManager = z2;
            this.role = role;
            this.message = message;
        }

        public final boolean getAllowed() {
            return this.allowed;
        }

        public final String getMessage() {
            return this.message;
        }

        public final String getRole() {
            return this.role;
        }

        public final boolean isManager() {
            return this.isManager;
        }
    }

    /* JADX INFO: compiled from: FirebaseCloudSync.kt */
    @Metadata(m913d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/cloud/FirebaseCloudSync$DeviceRequest;", "", "id", "", "userId", "email", "deviceId", "deviceName", "requestedAt", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getId", "()Ljava/lang/String;", "getUserId", "getEmail", "getDeviceId", "getDeviceName", "getRequestedAt", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* data */ class DeviceRequest {
        public static final int $stable = 0;
        private final String deviceId;
        private final String deviceName;
        private final String email;
        private final String id;
        private final long requestedAt;
        private final String userId;

        public static /* synthetic */ DeviceRequest copy$default(DeviceRequest deviceRequest, String str, String str2, String str3, String str4, String str5, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = deviceRequest.id;
            }
            if ((i & 2) != 0) {
                str2 = deviceRequest.userId;
            }
            if ((i & 4) != 0) {
                str3 = deviceRequest.email;
            }
            if ((i & 8) != 0) {
                str4 = deviceRequest.deviceId;
            }
            if ((i & 16) != 0) {
                str5 = deviceRequest.deviceName;
            }
            if ((i & 32) != 0) {
                j = deviceRequest.requestedAt;
            }
            long j2 = j;
            String str6 = str5;
            String str7 = str3;
            return deviceRequest.copy(str, str2, str7, str4, str6, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUserId() {
            return this.userId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getDeviceId() {
            return this.deviceId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getDeviceName() {
            return this.deviceName;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final long getRequestedAt() {
            return this.requestedAt;
        }

        public final DeviceRequest copy(String id, String userId, String email, String deviceId, String deviceName, long requestedAt) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(userId, "userId");
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(deviceName, "deviceName");
            return new DeviceRequest(id, userId, email, deviceId, deviceName, requestedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DeviceRequest)) {
                return false;
            }
            DeviceRequest deviceRequest = (DeviceRequest) other;
            return Intrinsics.areEqual(this.id, deviceRequest.id) && Intrinsics.areEqual(this.userId, deviceRequest.userId) && Intrinsics.areEqual(this.email, deviceRequest.email) && Intrinsics.areEqual(this.deviceId, deviceRequest.deviceId) && Intrinsics.areEqual(this.deviceName, deviceRequest.deviceName) && this.requestedAt == deviceRequest.requestedAt;
        }

        public int hashCode() {
            return (((((((((this.id.hashCode() * 31) + this.userId.hashCode()) * 31) + this.email.hashCode()) * 31) + this.deviceId.hashCode()) * 31) + this.deviceName.hashCode()) * 31) + Long.hashCode(this.requestedAt);
        }

        public String toString() {
            return "DeviceRequest(id=" + this.id + ", userId=" + this.userId + ", email=" + this.email + ", deviceId=" + this.deviceId + ", deviceName=" + this.deviceName + ", requestedAt=" + this.requestedAt + ")";
        }

        public DeviceRequest(String id, String userId, String email, String deviceId, String deviceName, long j) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(userId, "userId");
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(deviceName, "deviceName");
            this.id = id;
            this.userId = userId;
            this.email = email;
            this.deviceId = deviceId;
            this.deviceName = deviceName;
            this.requestedAt = j;
        }

        public final String getDeviceId() {
            return this.deviceId;
        }

        public final String getDeviceName() {
            return this.deviceName;
        }

        public final String getEmail() {
            return this.email;
        }

        public final String getId() {
            return this.id;
        }

        public final long getRequestedAt() {
            return this.requestedAt;
        }

        public final String getUserId() {
            return this.userId;
        }
    }

    public final String credentialsEmail(String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        String lowerCase = StringsKt.trim((CharSequence) identifier).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) "@", false, 2, (Object) null) ? lowerCase : lowerCase + "@accounts.masrof-manager.local";
    }

    private final String usernameKey(String username) {
        String lowerCase = StringsKt.trim((CharSequence) username).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return StringsKt.replace$default(lowerCase, DomExceptionUtils.SEPARATOR, "_", false, 4, (Object) null);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final String friendlyAuthError(Throwable error) {
        Intrinsics.checkNotNullParameter(error, "error");
        FirebaseAuthException firebaseAuthException = error instanceof FirebaseAuthException ? (FirebaseAuthException) error : null;
        String errorCode = firebaseAuthException != null ? firebaseAuthException.getErrorCode() : null;
        if (errorCode != null) {
            switch (errorCode.hashCode()) {
                case -1192524938:
                    if (errorCode.equals("ERROR_INVALID_CREDENTIAL")) {
                        return "بيانات الدخول غير صحيحة.";
                    }
                    break;
                case -1153235307:
                    if (errorCode.equals("ERROR_NETWORK_REQUEST_FAILED")) {
                        return "تعذر الاتصال بالخدمة. تحقق من الإنترنت وحاول مرة أخرى.";
                    }
                    break;
                case -1090616679:
                    if (errorCode.equals("ERROR_USER_NOT_FOUND")) {
                        return "بيانات الدخول غير صحيحة.";
                    }
                    break;
                case -431432636:
                    if (errorCode.equals("ERROR_WRONG_PASSWORD")) {
                        return "بيانات الدخول غير صحيحة.";
                    }
                    break;
                case 635219534:
                    if (errorCode.equals("ERROR_EMAIL_ALREADY_IN_USE")) {
                        return "هذا البريد أو اسم المستخدم مستخدم مسبقًا. استخدم تسجيل الدخول أو بريدًا آخر.";
                    }
                    break;
                case 636699458:
                    if (errorCode.equals("ERROR_TOO_MANY_REQUESTS")) {
                        return "تم تجاوز عدد المحاولات؛ انتظر قليلًا ثم حاول مرة أخرى.";
                    }
                    break;
                case 794520829:
                    if (errorCode.equals("ERROR_INVALID_EMAIL")) {
                        return "البريد الإلكتروني غير صحيح.";
                    }
                    break;
                case 1866228075:
                    if (errorCode.equals("ERROR_WEAK_PASSWORD")) {
                        return "كلمة المرور ضعيفة؛ استخدم 6 أحرف أو أكثر.";
                    }
                    break;
            }
        }
        FirebaseFirestoreException firebaseFirestoreException = error instanceof FirebaseFirestoreException ? (FirebaseFirestoreException) error : null;
        FirebaseFirestoreException.Code code = firebaseFirestoreException != null ? firebaseFirestoreException.getCode() : null;
        int i = code == null ? -1 : WhenMappings.$EnumSwitchMapping$0[code.ordinal()];
        if (i == 1) {
            return "تم تسجيل الدخول، لكن لا تملك صلاحية قراءة ملف المستخدم. انشر قواعد Firestore الصحيحة.";
        }
        if (i == 2) {
            return "تعذر الاتصال بقاعدة Firestore. تحقق من الإنترنت ثم أعد المحاولة.";
        }
        String message = error.getMessage();
        return message == null ? "تعذر إتمام عملية المصادقة." : message;
    }

    public final void initialize(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (FirebaseApp.getApps(context).isEmpty() && FirebaseApp.initializeApp(context) == null) {
            throw new IllegalArgumentException("تعذر تهيئة Firebase. تحقق من google-services.json".toString());
        }
    }

    private final FirebaseFirestore firestore() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseFirestore, "getInstance(...)");
        firebaseFirestore.enableNetwork();
        return firebaseFirestore;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object signIn(String str, String str2, Continuation<? super String> continuation) {
        C38511 c38511;
        String uid;
        if (continuation instanceof C38511) {
            c38511 = (C38511) continuation;
            if ((c38511.label & Integer.MIN_VALUE) != 0) {
                c38511.label -= Integer.MIN_VALUE;
            } else {
                c38511 = new C38511(continuation);
            }
        } else {
            c38511 = new C38511(continuation);
        }
        Object objAwait = c38511.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c38511.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAwait);
            Task<AuthResult> taskSignInWithEmailAndPassword = FirebaseAuth.getInstance().signInWithEmailAndPassword(credentialsEmail(str), str2);
            Intrinsics.checkNotNullExpressionValue(taskSignInWithEmailAndPassword, "signInWithEmailAndPassword(...)");
            c38511.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c38511.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            c38511.label = 1;
            objAwait = TasksKt.await(taskSignInWithEmailAndPassword, c38511);
            if (objAwait == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objAwait);
        }
        FirebaseUser user = ((AuthResult) objAwait).getUser();
        if (user == null || (uid = user.getUid()) == null) {
            throw new IllegalStateException("لم يعد Firebase مستخدمًا بعد تسجيل الدخول".toString());
        }
        return uid;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object signInByEmail(String str, String str2, Continuation<? super String> continuation) {
        C38521 c38521;
        String uid;
        if (continuation instanceof C38521) {
            c38521 = (C38521) continuation;
            if ((c38521.label & Integer.MIN_VALUE) != 0) {
                c38521.label -= Integer.MIN_VALUE;
            } else {
                c38521 = new C38521(continuation);
            }
        } else {
            c38521 = new C38521(continuation);
        }
        Object objAwait = c38521.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c38521.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAwait);
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            String lowerCase = StringsKt.trim((CharSequence) str).toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            Task<AuthResult> taskSignInWithEmailAndPassword = firebaseAuth.signInWithEmailAndPassword(lowerCase, str2);
            Intrinsics.checkNotNullExpressionValue(taskSignInWithEmailAndPassword, "signInWithEmailAndPassword(...)");
            c38521.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c38521.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            c38521.label = 1;
            objAwait = TasksKt.await(taskSignInWithEmailAndPassword, c38521);
            if (objAwait == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objAwait);
        }
        FirebaseUser user = ((AuthResult) objAwait).getUser();
        if (user == null || (uid = user.getUid()) == null) {
            throw new IllegalStateException("تعذر فتح الحساب".toString());
        }
        return uid;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object createFirstAccount(String str, String str2, Continuation<? super String> continuation) {
        C38451 c38451;
        String str3;
        String str4;
        String uid;
        if (continuation instanceof C38451) {
            c38451 = (C38451) continuation;
            if ((c38451.label & Integer.MIN_VALUE) != 0) {
                c38451.label -= Integer.MIN_VALUE;
            } else {
                c38451 = new C38451(continuation);
            }
        } else {
            c38451 = new C38451(continuation);
        }
        Object obj = c38451.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c38451.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String lowerCase = StringsKt.trim((CharSequence) str).toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String str5 = lowerCase;
            if (!StringsKt.contains$default((CharSequence) str5, (CharSequence) "@", false, 2, (Object) null) || !StringsKt.contains$default((CharSequence) str5, (CharSequence) ".", false, 2, (Object) null)) {
                throw new IllegalArgumentException("البريد الإلكتروني غير صحيح".toString());
            }
            Task<AuthResult> taskCreateUserWithEmailAndPassword = FirebaseAuth.getInstance().createUserWithEmailAndPassword(lowerCase, str2);
            Intrinsics.checkNotNullExpressionValue(taskCreateUserWithEmailAndPassword, "createUserWithEmailAndPassword(...)");
            c38451.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c38451.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            c38451.L$2 = lowerCase;
            c38451.label = 1;
            Object objAwait = TasksKt.await(taskCreateUserWithEmailAndPassword, c38451);
            if (objAwait != coroutine_suspended) {
                str3 = str;
                str4 = lowerCase;
                obj = objAwait;
            }
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            boolean z = c38451.Z$0;
            String str6 = (String) c38451.L$4;
            ResultKt.throwOnFailure(obj);
            return str6;
        }
        str4 = (String) c38451.L$2;
        str2 = (String) c38451.L$1;
        str3 = (String) c38451.L$0;
        ResultKt.throwOnFailure(obj);
        AuthResult authResult = (AuthResult) obj;
        FirebaseUser user = authResult.getUser();
        if (user == null || (uid = user.getUid()) == null) {
            throw new IllegalStateException("تعذر إنشاء حساب Firebase".toString());
        }
        boolean zAreEqual = Intrinsics.areEqual(str4, MANAGER_EMAIL);
        DocumentReference documentReferenceDocument = firestore().collection("users").document(uid);
        Pair[] pairArr = new Pair[7];
        pairArr[0] = TuplesKt.m921to("id", uid);
        pairArr[1] = TuplesKt.m921to("email", str4);
        pairArr[2] = TuplesKt.m921to(HintConstants.AUTOFILL_HINT_USERNAME, str4);
        pairArr[3] = TuplesKt.m921to("fullName", str4);
        pairArr[4] = TuplesKt.m921to("role", zAreEqual ? "SYSTEM_ADMIN" : "ADMIN_USER");
        pairArr[5] = TuplesKt.m921to("active", Boxing.boxBoolean(true));
        pairArr[6] = TuplesKt.m921to("createdAt", Boxing.boxLong(System.currentTimeMillis()));
        Task<Void> task = documentReferenceDocument.set(MapsKt.mapOf(pairArr));
        Intrinsics.checkNotNullExpressionValue(task, "set(...)");
        c38451.L$0 = SpillingKt.nullOutSpilledVariable(str3);
        c38451.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        c38451.L$2 = SpillingKt.nullOutSpilledVariable(str4);
        c38451.L$3 = SpillingKt.nullOutSpilledVariable(authResult);
        c38451.L$4 = uid;
        c38451.Z$0 = zAreEqual;
        c38451.label = 2;
        return TasksKt.await(task, c38451) == coroutine_suspended ? coroutine_suspended : uid;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00c3, code lost:
    
        if (r8 == r1) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object signInByUsername(String str, String str2, Continuation<? super String> continuation) {
        C38531 c38531;
        String strCredentialsEmail;
        Object obj;
        String uid;
        if (continuation instanceof C38531) {
            c38531 = (C38531) continuation;
            if ((c38531.label & Integer.MIN_VALUE) != 0) {
                c38531.label -= Integer.MIN_VALUE;
            } else {
                c38531 = new C38531(continuation);
            }
        } else {
            c38531 = new C38531(continuation);
        }
        Object objAwait = c38531.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c38531.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAwait);
            Task<DocumentSnapshot> task = firestore().collection("authAliases").document(usernameKey(str)).get();
            Intrinsics.checkNotNullExpressionValue(task, "get(...)");
            c38531.L$0 = str;
            c38531.L$1 = str2;
            c38531.label = 1;
            objAwait = TasksKt.await(task, c38531);
            if (objAwait != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            str2 = (String) c38531.L$1;
            str = (String) c38531.L$0;
            ResultKt.throwOnFailure(objAwait);
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objAwait);
        }
        FirebaseUser user = ((AuthResult) objAwait).getUser();
        if (user == null || (uid = user.getUid()) == null) {
            throw new IllegalStateException("تعذر فتح حساب المستخدم".toString());
        }
        return uid;
        Map<String, Object> data = ((DocumentSnapshot) objAwait).getData();
        if (data == null || (obj = data.get("authEmail")) == null || (strCredentialsEmail = obj.toString()) == null) {
            strCredentialsEmail = credentialsEmail(str);
        }
        Task<AuthResult> taskSignInWithEmailAndPassword = FirebaseAuth.getInstance().signInWithEmailAndPassword(strCredentialsEmail, str2);
        Intrinsics.checkNotNullExpressionValue(taskSignInWithEmailAndPassword, "signInWithEmailAndPassword(...)");
        c38531.L$0 = SpillingKt.nullOutSpilledVariable(str);
        c38531.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        c38531.L$2 = SpillingKt.nullOutSpilledVariable(data);
        c38531.L$3 = SpillingKt.nullOutSpilledVariable(strCredentialsEmail);
        c38531.label = 2;
        objAwait = TasksKt.await(taskSignInWithEmailAndPassword, c38531);
    }

    public static /* synthetic */ Object bindUsername$default(FirebaseCloudSync firebaseCloudSync, String str, String str2, String str3, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        return firebaseCloudSync.bindUsername(str, str2, str3, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x01c4, code lost:
    
        if (kotlinx.coroutines.tasks.TasksKt.await(r4, r3) == r5) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object bindUsername(String str, String str2, String str3, Continuation<? super Unit> continuation) {
        C38441 c38441;
        FirebaseCloudSync firebaseCloudSync;
        char c;
        char c2;
        FirebaseUser currentUser;
        int i;
        String str4;
        String str5;
        String str6;
        String displayName;
        String str7 = str2;
        if (continuation instanceof C38441) {
            c38441 = (C38441) continuation;
            if ((c38441.label & Integer.MIN_VALUE) != 0) {
                c38441.label -= Integer.MIN_VALUE;
                firebaseCloudSync = this;
            } else {
                firebaseCloudSync = this;
                c38441 = firebaseCloudSync.new C38441(continuation);
            }
        } else {
            firebaseCloudSync = this;
            c38441 = firebaseCloudSync.new C38441(continuation);
        }
        Object obj = c38441.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c38441.label;
        if (i2 == 0) {
            c = 3;
            c2 = 0;
            ResultKt.throwOnFailure(obj);
            currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser == null) {
                throw new IllegalStateException("يجب تسجيل الدخول بالبريد أولاً".toString());
            }
            String strUsernameKey = usernameKey(str);
            if (StringsKt.isBlank(strUsernameKey)) {
                throw new IllegalArgumentException("اسم المستخدم مطلوب".toString());
            }
            DocumentReference documentReferenceDocument = firebaseCloudSync.firestore().collection("authAliases").document(strUsernameKey);
            i = 2;
            String lowerCase = StringsKt.trim((CharSequence) str7).toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            Task<Void> task = documentReferenceDocument.set(MapsKt.mapOf(TuplesKt.m921to(HintConstants.AUTOFILL_HINT_USERNAME, StringsKt.trim((CharSequence) str).toString()), TuplesKt.m921to("authEmail", lowerCase), TuplesKt.m921to("uid", currentUser.getUid()), TuplesKt.m921to("updatedAt", Boxing.boxLong(System.currentTimeMillis()))), SetOptions.merge());
            Intrinsics.checkNotNullExpressionValue(task, "set(...)");
            c38441.L$0 = str;
            c38441.L$1 = str7;
            c38441.L$2 = str3;
            c38441.L$3 = currentUser;
            c38441.L$4 = SpillingKt.nullOutSpilledVariable(strUsernameKey);
            c38441.label = 1;
            if (TasksKt.await(task, c38441) != coroutine_suspended) {
                str4 = str;
                str5 = strUsernameKey;
                str6 = str3;
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            str5 = (String) c38441.L$4;
            FirebaseUser firebaseUser = (FirebaseUser) c38441.L$3;
            str6 = (String) c38441.L$2;
            c = 3;
            String str8 = (String) c38441.L$1;
            c2 = 0;
            str4 = (String) c38441.L$0;
            ResultKt.throwOnFailure(obj);
            currentUser = firebaseUser;
            str7 = str8;
            i = 2;
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
        DocumentReference documentReferenceDocument2 = firebaseCloudSync.firestore().collection("users").document(currentUser.getUid());
        Pair[] pairArr = new Pair[4];
        String str9 = str4;
        pairArr[c2] = TuplesKt.m921to(HintConstants.AUTOFILL_HINT_USERNAME, StringsKt.trim((CharSequence) str9).toString());
        String lowerCase2 = StringsKt.trim((CharSequence) str7).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
        pairArr[1] = TuplesKt.m921to("email", lowerCase2);
        if (str6 == null) {
            displayName = currentUser.getDisplayName();
            if (displayName == null) {
                displayName = StringsKt.trim((CharSequence) str9).toString();
            }
        } else {
            displayName = str6;
        }
        pairArr[i] = TuplesKt.m921to("fullName", displayName);
        pairArr[c] = TuplesKt.m921to("updatedAt", Boxing.boxLong(System.currentTimeMillis()));
        Task<Void> task2 = documentReferenceDocument2.set(MapsKt.mapOf(pairArr), SetOptions.merge());
        Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
        c38441.L$0 = SpillingKt.nullOutSpilledVariable(str4);
        c38441.L$1 = SpillingKt.nullOutSpilledVariable(str7);
        c38441.L$2 = SpillingKt.nullOutSpilledVariable(str6);
        c38441.L$3 = SpillingKt.nullOutSpilledVariable(currentUser);
        c38441.L$4 = SpillingKt.nullOutSpilledVariable(str5);
        c38441.label = i;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object register(String str, String str2, String str3, Continuation<? super String> continuation) {
        C38481 c38481;
        String strCredentialsEmail;
        Object objAwait;
        String uid;
        if (continuation instanceof C38481) {
            c38481 = (C38481) continuation;
            if ((c38481.label & Integer.MIN_VALUE) != 0) {
                c38481.label -= Integer.MIN_VALUE;
            } else {
                c38481 = new C38481(continuation);
            }
        } else {
            c38481 = new C38481(continuation);
        }
        Object obj = c38481.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c38481.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            strCredentialsEmail = credentialsEmail(str);
            Task<AuthResult> taskCreateUserWithEmailAndPassword = FirebaseAuth.getInstance().createUserWithEmailAndPassword(strCredentialsEmail, str2);
            Intrinsics.checkNotNullExpressionValue(taskCreateUserWithEmailAndPassword, "createUserWithEmailAndPassword(...)");
            c38481.L$0 = str;
            c38481.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            c38481.L$2 = str3;
            c38481.L$3 = strCredentialsEmail;
            c38481.label = 1;
            objAwait = TasksKt.await(taskCreateUserWithEmailAndPassword, c38481);
            if (objAwait != coroutine_suspended) {
            }
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            String str4 = (String) c38481.L$5;
            ResultKt.throwOnFailure(obj);
            return str4;
        }
        String str5 = (String) c38481.L$3;
        str3 = (String) c38481.L$2;
        str2 = (String) c38481.L$1;
        String str6 = (String) c38481.L$0;
        ResultKt.throwOnFailure(obj);
        strCredentialsEmail = str5;
        str = str6;
        objAwait = obj;
        AuthResult authResult = (AuthResult) objAwait;
        FirebaseUser user = authResult.getUser();
        if (user == null || (uid = user.getUid()) == null) {
            throw new IllegalStateException("تعذر إنشاء حساب Firebase".toString());
        }
        Task<Void> task = firestore().collection("users").document(uid).set(MapsKt.mapOf(TuplesKt.m921to("id", uid), TuplesKt.m921to(HintConstants.AUTOFILL_HINT_USERNAME, StringsKt.trim((CharSequence) str).toString()), TuplesKt.m921to("email", strCredentialsEmail), TuplesKt.m921to("fullName", str3), TuplesKt.m921to("role", "ADMIN_USER"), TuplesKt.m921to("active", Boxing.boxBoolean(true)), TuplesKt.m921to("createdAt", Boxing.boxLong(System.currentTimeMillis()))));
        Intrinsics.checkNotNullExpressionValue(task, "set(...)");
        c38481.L$0 = SpillingKt.nullOutSpilledVariable(str);
        c38481.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        c38481.L$2 = SpillingKt.nullOutSpilledVariable(str3);
        c38481.L$3 = SpillingKt.nullOutSpilledVariable(strCredentialsEmail);
        c38481.L$4 = SpillingKt.nullOutSpilledVariable(authResult);
        c38481.L$5 = uid;
        c38481.label = 2;
        return TasksKt.await(task, c38481) == coroutine_suspended ? coroutine_suspended : uid;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object currentProfile(Continuation<? super Map<String, ? extends Object>> continuation) {
        C38461 c38461;
        String uid;
        if (continuation instanceof C38461) {
            c38461 = (C38461) continuation;
            if ((c38461.label & Integer.MIN_VALUE) != 0) {
                c38461.label -= Integer.MIN_VALUE;
            } else {
                c38461 = new C38461(continuation);
            }
        } else {
            c38461 = new C38461(continuation);
        }
        Object objAwait = c38461.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c38461.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAwait);
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser == null || (uid = currentUser.getUid()) == null) {
                return MapsKt.emptyMap();
            }
            Task<DocumentSnapshot> task = firestore().collection("users").document(uid).get();
            Intrinsics.checkNotNullExpressionValue(task, "get(...)");
            c38461.L$0 = SpillingKt.nullOutSpilledVariable(uid);
            c38461.label = 1;
            objAwait = TasksKt.await(task, c38461);
            if (objAwait == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objAwait);
        }
        Map<String, Object> data = ((DocumentSnapshot) objAwait).getData();
        return data == null ? MapsKt.emptyMap() : data;
    }

    public final void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x008f, code lost:
    
        if (r9 == r1) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00ce, code lost:
    
        if (kotlinx.coroutines.tasks.TasksKt.await(r9, r0) == r1) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object sendPasswordReset(String str, Continuation<? super Unit> continuation) {
        C38501 c38501;
        String lowerCase;
        Object obj;
        String string;
        if (continuation instanceof C38501) {
            c38501 = (C38501) continuation;
            if ((c38501.label & Integer.MIN_VALUE) != 0) {
                c38501.label -= Integer.MIN_VALUE;
            } else {
                c38501 = new C38501(continuation);
            }
        } else {
            c38501 = new C38501(continuation);
        }
        Object objAwait = c38501.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c38501.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAwait);
            String str2 = str;
            if (!StringsKt.contains$default((CharSequence) str2, (CharSequence) "@", false, 2, (Object) null)) {
                Task<DocumentSnapshot> task = firestore().collection("authAliases").document(usernameKey(str)).get();
                Intrinsics.checkNotNullExpressionValue(task, "get(...)");
                c38501.L$0 = str;
                c38501.label = 1;
                objAwait = TasksKt.await(task, c38501);
            } else {
                lowerCase = StringsKt.trim((CharSequence) str2).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                Task<Void> taskSendPasswordResetEmail = FirebaseAuth.getInstance().sendPasswordResetEmail(lowerCase);
                Intrinsics.checkNotNullExpressionValue(taskSendPasswordResetEmail, "sendPasswordResetEmail(...)");
                c38501.L$0 = SpillingKt.nullOutSpilledVariable(str);
                c38501.L$1 = SpillingKt.nullOutSpilledVariable(lowerCase);
                c38501.label = 2;
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            str = (String) c38501.L$0;
            ResultKt.throwOnFailure(objAwait);
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objAwait);
        }
        return Unit.INSTANCE;
        Map<String, Object> data = ((DocumentSnapshot) objAwait).getData();
        lowerCase = (data == null || (obj = data.get("authEmail")) == null || (string = obj.toString()) == null) ? credentialsEmail(str) : string;
        Task<Void> taskSendPasswordResetEmail2 = FirebaseAuth.getInstance().sendPasswordResetEmail(lowerCase);
        Intrinsics.checkNotNullExpressionValue(taskSendPasswordResetEmail2, "sendPasswordResetEmail(...)");
        c38501.L$0 = SpillingKt.nullOutSpilledVariable(str);
        c38501.L$1 = SpillingKt.nullOutSpilledVariable(lowerCase);
        c38501.label = 2;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object sendEmailVerification(Continuation<? super Unit> continuation) {
        C38491 c38491;
        Task<Void> taskSendEmailVerification;
        if (continuation instanceof C38491) {
            c38491 = (C38491) continuation;
            if ((c38491.label & Integer.MIN_VALUE) != 0) {
                c38491.label -= Integer.MIN_VALUE;
            } else {
                c38491 = new C38491(continuation);
            }
        } else {
            c38491 = new C38491(continuation);
        }
        Object objAwait = c38491.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c38491.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAwait);
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null && (taskSendEmailVerification = currentUser.sendEmailVerification()) != null) {
                c38491.label = 1;
                objAwait = TasksKt.await(taskSendEmailVerification, c38491);
                if (objAwait == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            throw new IllegalStateException("لا يوجد حساب مسجل".toString());
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(objAwait);
        if (((Void) objAwait) != null) {
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("لا يوجد حساب مسجل".toString());
    }

    public final boolean isEmailVerified() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser != null && currentUser.isEmailVerified();
    }

    /* JADX WARN: Code duplicated, block: B:102:0x048c  */
    /* JADX WARN: Code duplicated, block: B:105:0x049d  */
    /* JADX WARN: Code duplicated, block: B:107:0x04a1  */
    /* JADX WARN: Code duplicated, block: B:114:0x0500  */
    /* JADX WARN: Code duplicated, block: B:128:0x0543  */
    /* JADX WARN: Code duplicated, block: B:131:0x0549  */
    /* JADX WARN: Code duplicated, block: B:132:0x054c  */
    /* JADX WARN: Code duplicated, block: B:135:0x059f  */
    /* JADX WARN: Code duplicated, block: B:136:0x05a2 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:141:0x05b1  */
    /* JADX WARN: Code duplicated, block: B:142:0x05b3  */
    /* JADX WARN: Code duplicated, block: B:145:0x05d4  */
    /* JADX WARN: Code duplicated, block: B:147:0x05dc  */
    /* JADX WARN: Code duplicated, block: B:151:0x0645  */
    /* JADX WARN: Code duplicated, block: B:153:0x0650 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:29:0x025c  */
    /* JADX WARN: Code duplicated, block: B:31:0x0265  */
    /* JADX WARN: Code duplicated, block: B:36:0x0283  */
    /* JADX WARN: Code duplicated, block: B:38:0x0287  */
    /* JADX WARN: Code duplicated, block: B:42:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:45:0x02d6  */
    /* JADX WARN: Code duplicated, block: B:47:0x02e2  */
    /* JADX WARN: Code duplicated, block: B:48:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:52:0x02f0  */
    /* JADX WARN: Code duplicated, block: B:55:0x0311  */
    /* JADX WARN: Code duplicated, block: B:59:0x031d  */
    /* JADX WARN: Code duplicated, block: B:62:0x0328  */
    /* JADX WARN: Code duplicated, block: B:66:0x0336 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:67:0x0338  */
    /* JADX WARN: Code duplicated, block: B:68:0x033b  */
    /* JADX WARN: Code duplicated, block: B:71:0x034c  */
    /* JADX WARN: Code duplicated, block: B:72:0x0353  */
    /* JADX WARN: Code duplicated, block: B:75:0x0359  */
    /* JADX WARN: Code duplicated, block: B:76:0x035c  */
    /* JADX WARN: Code duplicated, block: B:78:0x0360  */
    /* JADX WARN: Code duplicated, block: B:79:0x0365  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:83:0x03b9  */
    /* JADX WARN: Code duplicated, block: B:86:0x03ca  */
    /* JADX WARN: Code duplicated, block: B:89:0x03d4  */
    /* JADX WARN: Code duplicated, block: B:90:0x03f5  */
    /* JADX WARN: Code duplicated, block: B:94:0x0460  */
    /* JADX WARN: Code duplicated, block: B:97:0x0475 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:98:0x0477  */
    /* JADX WARN: Code duplicated, block: B:99:0x047c  */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x071e, code lost:
    
        if (kotlinx.coroutines.tasks.TasksKt.await(r0, r2) == r3) goto L156;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object authorizeCurrentDevice(Context context, Continuation<? super AccessDecision> continuation) {
        C38431 c38431;
        Context context2;
        FirebaseUser currentUser;
        String email;
        String lowerCase;
        boolean zAreEqual;
        DocumentReference documentReferenceDocument;
        Object objAwait;
        Context context3;
        Object obj;
        String string;
        Map<String, Object> data;
        String string2;
        Object obj2;
        String str;
        String string3;
        String string4;
        Object obj3;
        Boolean bool;
        boolean zBooleanValue;
        Map mapMapOf;
        Task<Void> task;
        Map<String, Object> map;
        Context context4;
        Object obj4;
        Object obj5;
        String string5;
        String str2;
        String str3;
        DocumentReference documentReference;
        DocumentReference documentReferenceDocument2;
        Object objAwait2;
        Context context5;
        Map<String, Object> map2;
        DocumentReference documentReference2;
        FirebaseUser firebaseUser;
        DocumentReference documentReference3;
        Object obj6;
        String str4;
        boolean z;
        Map map3;
        DocumentSnapshot documentSnapshot;
        Map<String, Object> data2;
        DocumentSnapshot documentSnapshot2;
        String str5;
        Map map4;
        int i;
        String str6;
        String str7;
        FirebaseUser firebaseUser2;
        Map map5;
        DocumentSnapshot documentSnapshot3;
        DocumentReference documentReference4;
        int i2;
        Map<String, Object> map6;
        Object objAwait3;
        Object obj7;
        boolean zAreEqual2;
        Object objM7781constructorimpl;
        int i3;
        boolean z2;
        String str8;
        String str9;
        Map map7;
        DocumentReference documentReference5;
        DocumentSnapshot documentSnapshot4;
        FirebaseUser firebaseUser3;
        String str10;
        String str11;
        String str12;
        String str13;
        boolean z3;
        Object objBoxLong;
        Task<Void> task2;
        Map map8;
        String str14;
        FirebaseUser firebaseUser4;
        String str15;
        boolean z4;
        Map<String, Object> map9;
        DocumentSnapshot documentSnapshot5;
        if (continuation instanceof C38431) {
            c38431 = (C38431) continuation;
            if ((c38431.label & Integer.MIN_VALUE) != 0) {
                c38431.label -= Integer.MIN_VALUE;
            } else {
                c38431 = new C38431(continuation);
            }
        } else {
            c38431 = new C38431(continuation);
        }
        Object obj8 = c38431.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        String str16 = "PENDING";
        String str17 = "deviceId";
        String str18 = "APPROVED";
        String str19 = "userId";
        String str20 = "";
        switch (c38431.label) {
            case 0:
                ResultKt.throwOnFailure(obj8);
                Task<Void> taskEnableNetwork = firestore().enableNetwork();
                Intrinsics.checkNotNullExpressionValue(taskEnableNetwork, "enableNetwork(...)");
                c38431.L$0 = context;
                c38431.label = 1;
                if (TasksKt.await(taskEnableNetwork, c38431) != coroutine_suspended) {
                    context2 = context;
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (currentUser == null) {
                        return new AccessDecision(false, false, "", "يجب تسجيل الدخول أولًا");
                    }
                    email = currentUser.getEmail();
                    if (email != null || (string = StringsKt.trim((CharSequence) email).toString()) == null) {
                        lowerCase = null;
                    } else {
                        lowerCase = string.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    }
                    if (lowerCase == null) {
                        lowerCase = "";
                    }
                    zAreEqual = Intrinsics.areEqual(lowerCase, MANAGER_EMAIL);
                    documentReferenceDocument = firestore().collection("users").document(currentUser.getUid());
                    Intrinsics.checkNotNullExpressionValue(documentReferenceDocument, "document(...)");
                    Task<DocumentSnapshot> task3 = documentReferenceDocument.get();
                    Intrinsics.checkNotNullExpressionValue(task3, "get(...)");
                    c38431.L$0 = context2;
                    c38431.L$1 = currentUser;
                    c38431.L$2 = lowerCase;
                    c38431.L$3 = documentReferenceDocument;
                    c38431.Z$0 = zAreEqual;
                    c38431.label = 2;
                    objAwait = TasksKt.await(task3, c38431);
                    if (objAwait != coroutine_suspended) {
                        context3 = context2;
                        obj = objAwait;
                        data = ((DocumentSnapshot) obj).getData();
                        if (zAreEqual) {
                            string2 = "SYSTEM_ADMIN";
                        } else if (data != null || (obj2 = data.get("role")) == null || (string2 = obj2.toString()) == null) {
                            string2 = "ADMIN_USER";
                        }
                        str = string2;
                        Pair[] pairArr = new Pair[7];
                        pairArr[0] = TuplesKt.m921to("id", currentUser.getUid());
                        pairArr[1] = TuplesKt.m921to("email", lowerCase);
                        if (data != null || (obj5 = data.get(HintConstants.AUTOFILL_HINT_USERNAME)) == null || (string3 = obj5.toString()) == null) {
                            string3 = lowerCase;
                        }
                        pairArr[2] = TuplesKt.m921to(HintConstants.AUTOFILL_HINT_USERNAME, string3);
                        if (data != null || (obj4 = data.get("fullName")) == null || (string4 = obj4.toString()) == null) {
                            if (zAreEqual) {
                                string4 = "مدير النظام";
                            } else {
                                string4 = lowerCase;
                            }
                        }
                        pairArr[3] = TuplesKt.m921to("fullName", string4);
                        pairArr[4] = TuplesKt.m921to("role", str);
                        if (data != null) {
                            obj3 = data.get("active");
                        } else {
                            obj3 = null;
                        }
                        if (obj3 instanceof Boolean) {
                            bool = (Boolean) obj3;
                        } else {
                            bool = null;
                        }
                        if (bool != null) {
                            zBooleanValue = bool.booleanValue();
                        } else {
                            zBooleanValue = true;
                        }
                        pairArr[5] = TuplesKt.m921to("active", Boxing.boxBoolean(zBooleanValue));
                        pairArr[6] = TuplesKt.m921to("updatedAt", Boxing.boxLong(System.currentTimeMillis()));
                        mapMapOf = MapsKt.mapOf(pairArr);
                        task = documentReferenceDocument.set(mapMapOf, SetOptions.merge());
                        Intrinsics.checkNotNullExpressionValue(task, "set(...)");
                        c38431.L$0 = context3;
                        c38431.L$1 = currentUser;
                        c38431.L$2 = lowerCase;
                        c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReferenceDocument);
                        c38431.L$4 = SpillingKt.nullOutSpilledVariable(data);
                        c38431.L$5 = str;
                        c38431.L$6 = SpillingKt.nullOutSpilledVariable(mapMapOf);
                        c38431.Z$0 = zAreEqual;
                        c38431.label = 3;
                        if (TasksKt.await(task, c38431) != coroutine_suspended) {
                            Context context6 = context3;
                            map = data;
                            context4 = context6;
                            string5 = Settings.Secure.getString(context4.getContentResolver(), "android_id");
                            if (string5 == null) {
                                string5 = str20;
                            }
                            str2 = string5;
                            if (StringsKt.isBlank(str2)) {
                                String uid = currentUser.getUid();
                                Intrinsics.checkNotNullExpressionValue(uid, "getUid(...)");
                                str2 = "unknown-" + StringsKt.take(uid, 8);
                            }
                            str3 = str2;
                            documentReference = documentReferenceDocument;
                            documentReferenceDocument2 = firestore().collection("devices").document(currentUser.getUid() + "_" + str3);
                            Intrinsics.checkNotNullExpressionValue(documentReferenceDocument2, "document(...)");
                            Task<DocumentSnapshot> task4 = documentReferenceDocument2.get();
                            Intrinsics.checkNotNullExpressionValue(task4, "get(...)");
                            c38431.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                            c38431.L$1 = currentUser;
                            c38431.L$2 = lowerCase;
                            c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference);
                            c38431.L$4 = SpillingKt.nullOutSpilledVariable(map);
                            c38431.L$5 = str;
                            c38431.L$6 = SpillingKt.nullOutSpilledVariable(mapMapOf);
                            c38431.L$7 = str3;
                            c38431.L$8 = documentReferenceDocument2;
                            c38431.Z$0 = zAreEqual;
                            c38431.label = 4;
                            objAwait2 = TasksKt.await(task4, c38431);
                            if (objAwait2 != coroutine_suspended) {
                                context5 = context4;
                                map2 = map;
                                documentReference2 = documentReference;
                                firebaseUser = currentUser;
                                documentReference3 = documentReferenceDocument2;
                                obj6 = objAwait2;
                                str4 = lowerCase;
                                z = zAreEqual;
                                map3 = mapMapOf;
                                documentSnapshot = (DocumentSnapshot) obj6;
                                data2 = documentSnapshot.getData();
                                try {
                                    try {
                                        if (z) {
                                            documentSnapshot2 = documentSnapshot;
                                            str5 = str19;
                                            map4 = map3;
                                        } else {
                                            if (data2 != null) {
                                                obj7 = data2.get(NotificationCompat.CATEGORY_STATUS);
                                            } else {
                                                obj7 = null;
                                            }
                                            documentSnapshot2 = documentSnapshot;
                                            String str21 = str18;
                                            zAreEqual2 = Intrinsics.areEqual(obj7, str21);
                                            str18 = str21;
                                            str5 = str19;
                                            if (zAreEqual2) {
                                                map4 = map3;
                                                if (Intrinsics.areEqual(data2.get(str5), firebaseUser.getUid())) {
                                                }
                                                Result.Companion companion = Result.INSTANCE;
                                                FirebaseCloudSync firebaseCloudSync = this;
                                                Task<String> token = FirebaseMessaging.getInstance().getToken();
                                                str6 = "_";
                                                Intrinsics.checkNotNullExpressionValue(token, "getToken(...)");
                                                c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                                c38431.L$1 = firebaseUser;
                                                c38431.L$2 = str4;
                                                c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                                c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                                c38431.L$5 = str;
                                                c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                                                c38431.L$7 = str3;
                                                c38431.L$8 = documentReference3;
                                                c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                                                c38431.L$10 = data2;
                                                c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                                                c38431.Z$0 = z;
                                                c38431.I$0 = i;
                                                c38431.I$1 = 0;
                                                c38431.label = 5;
                                                objAwait3 = TasksKt.await(token, c38431);
                                                if (objAwait3 != coroutine_suspended) {
                                                    str7 = str4;
                                                    firebaseUser2 = firebaseUser;
                                                    map5 = map4;
                                                    documentSnapshot3 = documentSnapshot2;
                                                    documentReference4 = documentReference3;
                                                    i2 = i;
                                                    map6 = data2;
                                                    try {
                                                        objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                                                    } catch (Throwable th) {
                                                        th = th;
                                                        Result.Companion companion2 = Result.INSTANCE;
                                                        objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                                                    }
                                                    i3 = i2;
                                                    z2 = z;
                                                    str8 = str7;
                                                    str9 = str;
                                                    map7 = map5;
                                                    documentReference5 = documentReference4;
                                                    documentSnapshot4 = documentSnapshot3;
                                                    firebaseUser3 = firebaseUser2;
                                                    str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                                                    if (str10 == null) {
                                                        str11 = str20;
                                                    } else {
                                                        str11 = str10;
                                                    }
                                                    Pair[] pairArr2 = new Pair[9];
                                                    pairArr2[0] = TuplesKt.m921to(str17, str3);
                                                    pairArr2[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                                                    pairArr2[2] = TuplesKt.m921to("email", str8);
                                                    str12 = str5;
                                                    pairArr2[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                                                    pairArr2[4] = TuplesKt.m921to("fcmToken", str11);
                                                    if (z2 && i3 == 0) {
                                                        str13 = str16;
                                                    } else {
                                                        str13 = str18;
                                                    }
                                                    pairArr2[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                                                    if (i3 != 0) {
                                                        z3 = true;
                                                    } else {
                                                        z3 = false;
                                                    }
                                                    pairArr2[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                                                    pairArr2[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                                                    if (map6 != null || (objBoxLong = map6.get("createdAt")) == null) {
                                                        objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                                    }
                                                    pairArr2[8] = TuplesKt.m921to("createdAt", objBoxLong);
                                                    task2 = documentReference5.set(MapsKt.mapOf(pairArr2), SetOptions.merge());
                                                    Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                                                    c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                                    c38431.L$1 = firebaseUser3;
                                                    c38431.L$2 = str8;
                                                    c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                                    c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                                    c38431.L$5 = str9;
                                                    c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                                                    c38431.L$7 = str3;
                                                    c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                                                    c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                                                    c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                                                    c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                                                    c38431.Z$0 = z2;
                                                    c38431.I$0 = i3;
                                                    c38431.label = 6;
                                                    if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                                        map8 = map7;
                                                        str14 = str11;
                                                        firebaseUser4 = firebaseUser3;
                                                        str15 = str9;
                                                        z4 = z2;
                                                        map9 = map6;
                                                        documentSnapshot5 = documentSnapshot4;
                                                        if (z4 && i3 == 0) {
                                                            FirebaseUser firebaseUser5 = firebaseUser4;
                                                            Task<Void> task5 = firestore().collection("accessRequests").document(firebaseUser4.getUid() + str6 + str3).set(MapsKt.mapOf(TuplesKt.m921to(str12, firebaseUser5.getUid()), TuplesKt.m921to("email", str8), TuplesKt.m921to(str17, str3), TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str16), TuplesKt.m921to("requestedAt", Boxing.boxLong(System.currentTimeMillis()))), SetOptions.merge());
                                                            Intrinsics.checkNotNullExpressionValue(task5, "set(...)");
                                                            c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                                            c38431.L$1 = SpillingKt.nullOutSpilledVariable(firebaseUser5);
                                                            c38431.L$2 = SpillingKt.nullOutSpilledVariable(str8);
                                                            c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                                            c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                                            c38431.L$5 = str15;
                                                            c38431.L$6 = SpillingKt.nullOutSpilledVariable(map8);
                                                            c38431.L$7 = SpillingKt.nullOutSpilledVariable(str3);
                                                            c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                                                            c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot5);
                                                            c38431.L$10 = SpillingKt.nullOutSpilledVariable(map9);
                                                            c38431.L$11 = SpillingKt.nullOutSpilledVariable(str14);
                                                            c38431.Z$0 = z4;
                                                            c38431.I$0 = i3;
                                                            c38431.label = 7;
                                                        } else {
                                                            return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                                                        }
                                                    }
                                                }
                                            } else {
                                                map4 = map3;
                                            }
                                            i = 0;
                                            Result.Companion companion3 = Result.INSTANCE;
                                            FirebaseCloudSync firebaseCloudSync2 = this;
                                            Task<String> token2 = FirebaseMessaging.getInstance().getToken();
                                            str6 = "_";
                                            Intrinsics.checkNotNullExpressionValue(token2, "getToken(...)");
                                            c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                            c38431.L$1 = firebaseUser;
                                            c38431.L$2 = str4;
                                            c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                            c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                            c38431.L$5 = str;
                                            c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                                            c38431.L$7 = str3;
                                            c38431.L$8 = documentReference3;
                                            c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                                            c38431.L$10 = data2;
                                            c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                                            c38431.Z$0 = z;
                                            c38431.I$0 = i;
                                            c38431.I$1 = 0;
                                            c38431.label = 5;
                                            objAwait3 = TasksKt.await(token2, c38431);
                                            if (objAwait3 != coroutine_suspended) {
                                                str7 = str4;
                                                firebaseUser2 = firebaseUser;
                                                map5 = map4;
                                                documentSnapshot3 = documentSnapshot2;
                                                documentReference4 = documentReference3;
                                                i2 = i;
                                                map6 = data2;
                                                objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                                                i3 = i2;
                                                z2 = z;
                                                str8 = str7;
                                                str9 = str;
                                                map7 = map5;
                                                documentReference5 = documentReference4;
                                                documentSnapshot4 = documentSnapshot3;
                                                firebaseUser3 = firebaseUser2;
                                                str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                                                if (str10 == null) {
                                                    str11 = str20;
                                                } else {
                                                    str11 = str10;
                                                }
                                                Pair[] pairArr3 = new Pair[9];
                                                pairArr3[0] = TuplesKt.m921to(str17, str3);
                                                pairArr3[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                                                pairArr3[2] = TuplesKt.m921to("email", str8);
                                                str12 = str5;
                                                pairArr3[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                                                pairArr3[4] = TuplesKt.m921to("fcmToken", str11);
                                                if (z2) {
                                                    str13 = str18;
                                                } else {
                                                    str13 = str16;
                                                }
                                                pairArr3[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                                                if (i3 != 0) {
                                                    z3 = true;
                                                } else {
                                                    z3 = false;
                                                }
                                                pairArr3[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                                                pairArr3[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                                                if (map6 != null) {
                                                    objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                                } else {
                                                    objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                                }
                                                pairArr3[8] = TuplesKt.m921to("createdAt", objBoxLong);
                                                task2 = documentReference5.set(MapsKt.mapOf(pairArr3), SetOptions.merge());
                                                Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                                                c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                                c38431.L$1 = firebaseUser3;
                                                c38431.L$2 = str8;
                                                c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                                c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                                c38431.L$5 = str9;
                                                c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                                                c38431.L$7 = str3;
                                                c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                                                c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                                                c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                                                c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                                                c38431.Z$0 = z2;
                                                c38431.I$0 = i3;
                                                c38431.label = 6;
                                                if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                                    map8 = map7;
                                                    str14 = str11;
                                                    firebaseUser4 = firebaseUser3;
                                                    str15 = str9;
                                                    z4 = z2;
                                                    map9 = map6;
                                                    documentSnapshot5 = documentSnapshot4;
                                                    if (z4) {
                                                    }
                                                    return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                                                }
                                            }
                                        }
                                        Intrinsics.checkNotNullExpressionValue(token2, "getToken(...)");
                                        c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                        c38431.L$1 = firebaseUser;
                                        c38431.L$2 = str4;
                                        c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                        c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                        c38431.L$5 = str;
                                        c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                                        c38431.L$7 = str3;
                                        c38431.L$8 = documentReference3;
                                        c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                                        c38431.L$10 = data2;
                                        c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                                        c38431.Z$0 = z;
                                        c38431.I$0 = i;
                                        c38431.I$1 = 0;
                                        c38431.label = 5;
                                        objAwait3 = TasksKt.await(token2, c38431);
                                        if (objAwait3 != coroutine_suspended) {
                                            str7 = str4;
                                            firebaseUser2 = firebaseUser;
                                            map5 = map4;
                                            documentSnapshot3 = documentSnapshot2;
                                            documentReference4 = documentReference3;
                                            i2 = i;
                                            map6 = data2;
                                            objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                                            i3 = i2;
                                            z2 = z;
                                            str8 = str7;
                                            str9 = str;
                                            map7 = map5;
                                            documentReference5 = documentReference4;
                                            documentSnapshot4 = documentSnapshot3;
                                            firebaseUser3 = firebaseUser2;
                                            str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                                            if (str10 == null) {
                                                str11 = str20;
                                            } else {
                                                str11 = str10;
                                            }
                                            Pair[] pairArr4 = new Pair[9];
                                            pairArr4[0] = TuplesKt.m921to(str17, str3);
                                            pairArr4[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                                            pairArr4[2] = TuplesKt.m921to("email", str8);
                                            str12 = str5;
                                            pairArr4[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                                            pairArr4[4] = TuplesKt.m921to("fcmToken", str11);
                                            if (z2) {
                                                str13 = str18;
                                            } else {
                                                str13 = str16;
                                            }
                                            pairArr4[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                                            if (i3 != 0) {
                                                z3 = true;
                                            } else {
                                                z3 = false;
                                            }
                                            pairArr4[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                                            pairArr4[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                                            if (map6 != null) {
                                                objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                            } else {
                                                objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                            }
                                            pairArr4[8] = TuplesKt.m921to("createdAt", objBoxLong);
                                            task2 = documentReference5.set(MapsKt.mapOf(pairArr4), SetOptions.merge());
                                            Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                                            c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                            c38431.L$1 = firebaseUser3;
                                            c38431.L$2 = str8;
                                            c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                            c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                            c38431.L$5 = str9;
                                            c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                                            c38431.L$7 = str3;
                                            c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                                            c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                                            c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                                            c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                                            c38431.Z$0 = z2;
                                            c38431.I$0 = i3;
                                            c38431.label = 6;
                                            if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                                map8 = map7;
                                                str14 = str11;
                                                firebaseUser4 = firebaseUser3;
                                                str15 = str9;
                                                z4 = z2;
                                                map9 = map6;
                                                documentSnapshot5 = documentSnapshot4;
                                                if (z4) {
                                                }
                                                return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                                            }
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        str7 = str4;
                                        firebaseUser2 = firebaseUser;
                                        map5 = map4;
                                        documentSnapshot3 = documentSnapshot2;
                                        documentReference4 = documentReference3;
                                        i2 = i;
                                        map6 = data2;
                                        Result.Companion companion4 = Result.INSTANCE;
                                        objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                                        i3 = i2;
                                        z2 = z;
                                        str8 = str7;
                                        str9 = str;
                                        map7 = map5;
                                        documentReference5 = documentReference4;
                                        documentSnapshot4 = documentSnapshot3;
                                        firebaseUser3 = firebaseUser2;
                                        str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                                        if (str10 == null) {
                                            str11 = str20;
                                        } else {
                                            str11 = str10;
                                        }
                                        Pair[] pairArr5 = new Pair[9];
                                        pairArr5[0] = TuplesKt.m921to(str17, str3);
                                        pairArr5[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                                        pairArr5[2] = TuplesKt.m921to("email", str8);
                                        str12 = str5;
                                        pairArr5[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                                        pairArr5[4] = TuplesKt.m921to("fcmToken", str11);
                                        if (z2) {
                                            str13 = str18;
                                        } else {
                                            str13 = str16;
                                        }
                                        pairArr5[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                                        if (i3 != 0) {
                                            z3 = true;
                                        } else {
                                            z3 = false;
                                        }
                                        pairArr5[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                                        pairArr5[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                                        if (map6 != null) {
                                            objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                        } else {
                                            objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                        }
                                        pairArr5[8] = TuplesKt.m921to("createdAt", objBoxLong);
                                        task2 = documentReference5.set(MapsKt.mapOf(pairArr5), SetOptions.merge());
                                        Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                                        c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                        c38431.L$1 = firebaseUser3;
                                        c38431.L$2 = str8;
                                        c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                        c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                        c38431.L$5 = str9;
                                        c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                                        c38431.L$7 = str3;
                                        c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                                        c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                                        c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                                        c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                                        c38431.Z$0 = z2;
                                        c38431.I$0 = i3;
                                        c38431.label = 6;
                                        if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                            map8 = map7;
                                            str14 = str11;
                                            firebaseUser4 = firebaseUser3;
                                            str15 = str9;
                                            z4 = z2;
                                            map9 = map6;
                                            documentSnapshot5 = documentSnapshot4;
                                            if (z4) {
                                            }
                                            return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                                        }
                                        return coroutine_suspended;
                                    }
                                    Result.Companion companion5 = Result.INSTANCE;
                                    FirebaseCloudSync firebaseCloudSync3 = this;
                                    Task<String> token3 = FirebaseMessaging.getInstance().getToken();
                                    str6 = "_";
                                } catch (Throwable th3) {
                                    th = th3;
                                    str6 = "_";
                                }
                                i = 1;
                            }
                        }
                    }
                    break;
                }
                return coroutine_suspended;
            case 1:
                context2 = (Context) c38431.L$0;
                ResultKt.throwOnFailure(obj8);
                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser == null) {
                    return new AccessDecision(false, false, "", "يجب تسجيل الدخول أولًا");
                }
                email = currentUser.getEmail();
                if (email != null) {
                    lowerCase = null;
                } else {
                    lowerCase = null;
                }
                if (lowerCase == null) {
                    lowerCase = "";
                }
                zAreEqual = Intrinsics.areEqual(lowerCase, MANAGER_EMAIL);
                documentReferenceDocument = firestore().collection("users").document(currentUser.getUid());
                Intrinsics.checkNotNullExpressionValue(documentReferenceDocument, "document(...)");
                Task<DocumentSnapshot> task6 = documentReferenceDocument.get();
                Intrinsics.checkNotNullExpressionValue(task6, "get(...)");
                c38431.L$0 = context2;
                c38431.L$1 = currentUser;
                c38431.L$2 = lowerCase;
                c38431.L$3 = documentReferenceDocument;
                c38431.Z$0 = zAreEqual;
                c38431.label = 2;
                objAwait = TasksKt.await(task6, c38431);
                if (objAwait != coroutine_suspended) {
                    context3 = context2;
                    obj = objAwait;
                    data = ((DocumentSnapshot) obj).getData();
                    if (zAreEqual) {
                        string2 = "SYSTEM_ADMIN";
                    } else if (data != null) {
                        string2 = "ADMIN_USER";
                    } else {
                        string2 = "ADMIN_USER";
                    }
                    str = string2;
                    Pair[] pairArr6 = new Pair[7];
                    pairArr6[0] = TuplesKt.m921to("id", currentUser.getUid());
                    pairArr6[1] = TuplesKt.m921to("email", lowerCase);
                    if (data != null) {
                        string3 = lowerCase;
                    } else {
                        string3 = lowerCase;
                    }
                    pairArr6[2] = TuplesKt.m921to(HintConstants.AUTOFILL_HINT_USERNAME, string3);
                    if (data != null) {
                        if (zAreEqual) {
                            string4 = "مدير النظام";
                        } else {
                            string4 = lowerCase;
                        }
                    } else if (zAreEqual) {
                        string4 = "مدير النظام";
                    } else {
                        string4 = lowerCase;
                    }
                    pairArr6[3] = TuplesKt.m921to("fullName", string4);
                    pairArr6[4] = TuplesKt.m921to("role", str);
                    if (data != null) {
                        obj3 = data.get("active");
                    } else {
                        obj3 = null;
                    }
                    if (obj3 instanceof Boolean) {
                        bool = (Boolean) obj3;
                    } else {
                        bool = null;
                    }
                    if (bool != null) {
                        zBooleanValue = bool.booleanValue();
                    } else {
                        zBooleanValue = true;
                    }
                    pairArr6[5] = TuplesKt.m921to("active", Boxing.boxBoolean(zBooleanValue));
                    pairArr6[6] = TuplesKt.m921to("updatedAt", Boxing.boxLong(System.currentTimeMillis()));
                    mapMapOf = MapsKt.mapOf(pairArr6);
                    task = documentReferenceDocument.set(mapMapOf, SetOptions.merge());
                    Intrinsics.checkNotNullExpressionValue(task, "set(...)");
                    c38431.L$0 = context3;
                    c38431.L$1 = currentUser;
                    c38431.L$2 = lowerCase;
                    c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReferenceDocument);
                    c38431.L$4 = SpillingKt.nullOutSpilledVariable(data);
                    c38431.L$5 = str;
                    c38431.L$6 = SpillingKt.nullOutSpilledVariable(mapMapOf);
                    c38431.Z$0 = zAreEqual;
                    c38431.label = 3;
                    if (TasksKt.await(task, c38431) != coroutine_suspended) {
                        Context context7 = context3;
                        map = data;
                        context4 = context7;
                        string5 = Settings.Secure.getString(context4.getContentResolver(), "android_id");
                        if (string5 == null) {
                            string5 = str20;
                        }
                        str2 = string5;
                        if (StringsKt.isBlank(str2)) {
                            String uid2 = currentUser.getUid();
                            Intrinsics.checkNotNullExpressionValue(uid2, "getUid(...)");
                            str2 = "unknown-" + StringsKt.take(uid2, 8);
                        }
                        str3 = str2;
                        documentReference = documentReferenceDocument;
                        documentReferenceDocument2 = firestore().collection("devices").document(currentUser.getUid() + "_" + str3);
                        Intrinsics.checkNotNullExpressionValue(documentReferenceDocument2, "document(...)");
                        Task<DocumentSnapshot> task7 = documentReferenceDocument2.get();
                        Intrinsics.checkNotNullExpressionValue(task7, "get(...)");
                        c38431.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                        c38431.L$1 = currentUser;
                        c38431.L$2 = lowerCase;
                        c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference);
                        c38431.L$4 = SpillingKt.nullOutSpilledVariable(map);
                        c38431.L$5 = str;
                        c38431.L$6 = SpillingKt.nullOutSpilledVariable(mapMapOf);
                        c38431.L$7 = str3;
                        c38431.L$8 = documentReferenceDocument2;
                        c38431.Z$0 = zAreEqual;
                        c38431.label = 4;
                        objAwait2 = TasksKt.await(task7, c38431);
                        if (objAwait2 != coroutine_suspended) {
                            context5 = context4;
                            map2 = map;
                            documentReference2 = documentReference;
                            firebaseUser = currentUser;
                            documentReference3 = documentReferenceDocument2;
                            obj6 = objAwait2;
                            str4 = lowerCase;
                            z = zAreEqual;
                            map3 = mapMapOf;
                            documentSnapshot = (DocumentSnapshot) obj6;
                            data2 = documentSnapshot.getData();
                            if (z) {
                                if (data2 != null) {
                                    obj7 = data2.get(NotificationCompat.CATEGORY_STATUS);
                                } else {
                                    obj7 = null;
                                }
                                documentSnapshot2 = documentSnapshot;
                                String str22 = str18;
                                zAreEqual2 = Intrinsics.areEqual(obj7, str22);
                                str18 = str22;
                                str5 = str19;
                                if (zAreEqual2) {
                                    map4 = map3;
                                    if (Intrinsics.areEqual(data2.get(str5), firebaseUser.getUid())) {
                                    }
                                    Result.Companion companion6 = Result.INSTANCE;
                                    FirebaseCloudSync firebaseCloudSync4 = this;
                                    Task<String> token4 = FirebaseMessaging.getInstance().getToken();
                                    str6 = "_";
                                    Intrinsics.checkNotNullExpressionValue(token4, "getToken(...)");
                                    c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                    c38431.L$1 = firebaseUser;
                                    c38431.L$2 = str4;
                                    c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                    c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                    c38431.L$5 = str;
                                    c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                                    c38431.L$7 = str3;
                                    c38431.L$8 = documentReference3;
                                    c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                                    c38431.L$10 = data2;
                                    c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                                    c38431.Z$0 = z;
                                    c38431.I$0 = i;
                                    c38431.I$1 = 0;
                                    c38431.label = 5;
                                    objAwait3 = TasksKt.await(token4, c38431);
                                    if (objAwait3 != coroutine_suspended) {
                                        str7 = str4;
                                        firebaseUser2 = firebaseUser;
                                        map5 = map4;
                                        documentSnapshot3 = documentSnapshot2;
                                        documentReference4 = documentReference3;
                                        i2 = i;
                                        map6 = data2;
                                        objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                                        i3 = i2;
                                        z2 = z;
                                        str8 = str7;
                                        str9 = str;
                                        map7 = map5;
                                        documentReference5 = documentReference4;
                                        documentSnapshot4 = documentSnapshot3;
                                        firebaseUser3 = firebaseUser2;
                                        str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                                        if (str10 == null) {
                                            str11 = str20;
                                        } else {
                                            str11 = str10;
                                        }
                                        Pair[] pairArr7 = new Pair[9];
                                        pairArr7[0] = TuplesKt.m921to(str17, str3);
                                        pairArr7[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                                        pairArr7[2] = TuplesKt.m921to("email", str8);
                                        str12 = str5;
                                        pairArr7[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                                        pairArr7[4] = TuplesKt.m921to("fcmToken", str11);
                                        if (z2) {
                                            str13 = str18;
                                        } else {
                                            str13 = str16;
                                        }
                                        pairArr7[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                                        if (i3 != 0) {
                                            z3 = true;
                                        } else {
                                            z3 = false;
                                        }
                                        pairArr7[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                                        pairArr7[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                                        if (map6 != null) {
                                            objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                        } else {
                                            objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                        }
                                        pairArr7[8] = TuplesKt.m921to("createdAt", objBoxLong);
                                        task2 = documentReference5.set(MapsKt.mapOf(pairArr7), SetOptions.merge());
                                        Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                                        c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                        c38431.L$1 = firebaseUser3;
                                        c38431.L$2 = str8;
                                        c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                        c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                        c38431.L$5 = str9;
                                        c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                                        c38431.L$7 = str3;
                                        c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                                        c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                                        c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                                        c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                                        c38431.Z$0 = z2;
                                        c38431.I$0 = i3;
                                        c38431.label = 6;
                                        if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                            map8 = map7;
                                            str14 = str11;
                                            firebaseUser4 = firebaseUser3;
                                            str15 = str9;
                                            z4 = z2;
                                            map9 = map6;
                                            documentSnapshot5 = documentSnapshot4;
                                            if (z4) {
                                            }
                                            return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                                        }
                                    }
                                } else {
                                    map4 = map3;
                                }
                                i = 0;
                                Result.Companion companion7 = Result.INSTANCE;
                                FirebaseCloudSync firebaseCloudSync5 = this;
                                Task<String> token5 = FirebaseMessaging.getInstance().getToken();
                                str6 = "_";
                                Intrinsics.checkNotNullExpressionValue(token5, "getToken(...)");
                                c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                c38431.L$1 = firebaseUser;
                                c38431.L$2 = str4;
                                c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                c38431.L$5 = str;
                                c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                                c38431.L$7 = str3;
                                c38431.L$8 = documentReference3;
                                c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                                c38431.L$10 = data2;
                                c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                                c38431.Z$0 = z;
                                c38431.I$0 = i;
                                c38431.I$1 = 0;
                                c38431.label = 5;
                                objAwait3 = TasksKt.await(token5, c38431);
                                if (objAwait3 != coroutine_suspended) {
                                    str7 = str4;
                                    firebaseUser2 = firebaseUser;
                                    map5 = map4;
                                    documentSnapshot3 = documentSnapshot2;
                                    documentReference4 = documentReference3;
                                    i2 = i;
                                    map6 = data2;
                                    objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                                    i3 = i2;
                                    z2 = z;
                                    str8 = str7;
                                    str9 = str;
                                    map7 = map5;
                                    documentReference5 = documentReference4;
                                    documentSnapshot4 = documentSnapshot3;
                                    firebaseUser3 = firebaseUser2;
                                    str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                                    if (str10 == null) {
                                        str11 = str20;
                                    } else {
                                        str11 = str10;
                                    }
                                    Pair[] pairArr8 = new Pair[9];
                                    pairArr8[0] = TuplesKt.m921to(str17, str3);
                                    pairArr8[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                                    pairArr8[2] = TuplesKt.m921to("email", str8);
                                    str12 = str5;
                                    pairArr8[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                                    pairArr8[4] = TuplesKt.m921to("fcmToken", str11);
                                    if (z2) {
                                        str13 = str18;
                                    } else {
                                        str13 = str16;
                                    }
                                    pairArr8[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                                    if (i3 != 0) {
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                    }
                                    pairArr8[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                                    pairArr8[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                                    if (map6 != null) {
                                        objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                    } else {
                                        objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                    }
                                    pairArr8[8] = TuplesKt.m921to("createdAt", objBoxLong);
                                    task2 = documentReference5.set(MapsKt.mapOf(pairArr8), SetOptions.merge());
                                    Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                                    c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                    c38431.L$1 = firebaseUser3;
                                    c38431.L$2 = str8;
                                    c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                    c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                    c38431.L$5 = str9;
                                    c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                                    c38431.L$7 = str3;
                                    c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                                    c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                                    c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                                    c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                                    c38431.Z$0 = z2;
                                    c38431.I$0 = i3;
                                    c38431.label = 6;
                                    if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                        map8 = map7;
                                        str14 = str11;
                                        firebaseUser4 = firebaseUser3;
                                        str15 = str9;
                                        z4 = z2;
                                        map9 = map6;
                                        documentSnapshot5 = documentSnapshot4;
                                        if (z4) {
                                        }
                                        return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                                    }
                                }
                            } else {
                                documentSnapshot2 = documentSnapshot;
                                str5 = str19;
                                map4 = map3;
                            }
                            i = 1;
                            Result.Companion companion8 = Result.INSTANCE;
                            FirebaseCloudSync firebaseCloudSync6 = this;
                            Task<String> token6 = FirebaseMessaging.getInstance().getToken();
                            str6 = "_";
                            Intrinsics.checkNotNullExpressionValue(token6, "getToken(...)");
                            c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                            c38431.L$1 = firebaseUser;
                            c38431.L$2 = str4;
                            c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                            c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                            c38431.L$5 = str;
                            c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                            c38431.L$7 = str3;
                            c38431.L$8 = documentReference3;
                            c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                            c38431.L$10 = data2;
                            c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                            c38431.Z$0 = z;
                            c38431.I$0 = i;
                            c38431.I$1 = 0;
                            c38431.label = 5;
                            objAwait3 = TasksKt.await(token6, c38431);
                            if (objAwait3 != coroutine_suspended) {
                                str7 = str4;
                                firebaseUser2 = firebaseUser;
                                map5 = map4;
                                documentSnapshot3 = documentSnapshot2;
                                documentReference4 = documentReference3;
                                i2 = i;
                                map6 = data2;
                                objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                                i3 = i2;
                                z2 = z;
                                str8 = str7;
                                str9 = str;
                                map7 = map5;
                                documentReference5 = documentReference4;
                                documentSnapshot4 = documentSnapshot3;
                                firebaseUser3 = firebaseUser2;
                                str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                                if (str10 == null) {
                                    str11 = str20;
                                } else {
                                    str11 = str10;
                                }
                                Pair[] pairArr9 = new Pair[9];
                                pairArr9[0] = TuplesKt.m921to(str17, str3);
                                pairArr9[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                                pairArr9[2] = TuplesKt.m921to("email", str8);
                                str12 = str5;
                                pairArr9[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                                pairArr9[4] = TuplesKt.m921to("fcmToken", str11);
                                if (z2) {
                                    str13 = str18;
                                } else {
                                    str13 = str16;
                                }
                                pairArr9[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                                if (i3 != 0) {
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                                pairArr9[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                                pairArr9[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                                if (map6 != null) {
                                    objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                } else {
                                    objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                }
                                pairArr9[8] = TuplesKt.m921to("createdAt", objBoxLong);
                                task2 = documentReference5.set(MapsKt.mapOf(pairArr9), SetOptions.merge());
                                Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                                c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                c38431.L$1 = firebaseUser3;
                                c38431.L$2 = str8;
                                c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                c38431.L$5 = str9;
                                c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                                c38431.L$7 = str3;
                                c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                                c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                                c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                                c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                                c38431.Z$0 = z2;
                                c38431.I$0 = i3;
                                c38431.label = 6;
                                if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                    map8 = map7;
                                    str14 = str11;
                                    firebaseUser4 = firebaseUser3;
                                    str15 = str9;
                                    z4 = z2;
                                    map9 = map6;
                                    documentSnapshot5 = documentSnapshot4;
                                    if (z4) {
                                    }
                                    return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                                }
                            }
                        }
                    }
                }
                return coroutine_suspended;
            case 2:
                boolean z5 = c38431.Z$0;
                DocumentReference documentReference6 = (DocumentReference) c38431.L$3;
                lowerCase = (String) c38431.L$2;
                FirebaseUser firebaseUser6 = (FirebaseUser) c38431.L$1;
                Context context8 = (Context) c38431.L$0;
                ResultKt.throwOnFailure(obj8);
                documentReferenceDocument = documentReference6;
                currentUser = firebaseUser6;
                zAreEqual = z5;
                context3 = context8;
                obj = obj8;
                data = ((DocumentSnapshot) obj).getData();
                if (zAreEqual) {
                    string2 = "SYSTEM_ADMIN";
                } else if (data != null) {
                    string2 = "ADMIN_USER";
                } else {
                    string2 = "ADMIN_USER";
                }
                str = string2;
                Pair[] pairArr10 = new Pair[7];
                pairArr10[0] = TuplesKt.m921to("id", currentUser.getUid());
                pairArr10[1] = TuplesKt.m921to("email", lowerCase);
                if (data != null) {
                    string3 = lowerCase;
                } else {
                    string3 = lowerCase;
                }
                pairArr10[2] = TuplesKt.m921to(HintConstants.AUTOFILL_HINT_USERNAME, string3);
                if (data != null) {
                    if (zAreEqual) {
                        string4 = "مدير النظام";
                    } else {
                        string4 = lowerCase;
                    }
                } else if (zAreEqual) {
                    string4 = "مدير النظام";
                } else {
                    string4 = lowerCase;
                }
                pairArr10[3] = TuplesKt.m921to("fullName", string4);
                pairArr10[4] = TuplesKt.m921to("role", str);
                if (data != null) {
                    obj3 = data.get("active");
                } else {
                    obj3 = null;
                }
                if (obj3 instanceof Boolean) {
                    bool = (Boolean) obj3;
                } else {
                    bool = null;
                }
                if (bool != null) {
                    zBooleanValue = bool.booleanValue();
                } else {
                    zBooleanValue = true;
                }
                pairArr10[5] = TuplesKt.m921to("active", Boxing.boxBoolean(zBooleanValue));
                pairArr10[6] = TuplesKt.m921to("updatedAt", Boxing.boxLong(System.currentTimeMillis()));
                mapMapOf = MapsKt.mapOf(pairArr10);
                task = documentReferenceDocument.set(mapMapOf, SetOptions.merge());
                Intrinsics.checkNotNullExpressionValue(task, "set(...)");
                c38431.L$0 = context3;
                c38431.L$1 = currentUser;
                c38431.L$2 = lowerCase;
                c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReferenceDocument);
                c38431.L$4 = SpillingKt.nullOutSpilledVariable(data);
                c38431.L$5 = str;
                c38431.L$6 = SpillingKt.nullOutSpilledVariable(mapMapOf);
                c38431.Z$0 = zAreEqual;
                c38431.label = 3;
                if (TasksKt.await(task, c38431) != coroutine_suspended) {
                    Context context9 = context3;
                    map = data;
                    context4 = context9;
                    string5 = Settings.Secure.getString(context4.getContentResolver(), "android_id");
                    if (string5 == null) {
                        string5 = str20;
                    }
                    str2 = string5;
                    if (StringsKt.isBlank(str2)) {
                        String uid3 = currentUser.getUid();
                        Intrinsics.checkNotNullExpressionValue(uid3, "getUid(...)");
                        str2 = "unknown-" + StringsKt.take(uid3, 8);
                    }
                    str3 = str2;
                    documentReference = documentReferenceDocument;
                    documentReferenceDocument2 = firestore().collection("devices").document(currentUser.getUid() + "_" + str3);
                    Intrinsics.checkNotNullExpressionValue(documentReferenceDocument2, "document(...)");
                    Task<DocumentSnapshot> task8 = documentReferenceDocument2.get();
                    Intrinsics.checkNotNullExpressionValue(task8, "get(...)");
                    c38431.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                    c38431.L$1 = currentUser;
                    c38431.L$2 = lowerCase;
                    c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference);
                    c38431.L$4 = SpillingKt.nullOutSpilledVariable(map);
                    c38431.L$5 = str;
                    c38431.L$6 = SpillingKt.nullOutSpilledVariable(mapMapOf);
                    c38431.L$7 = str3;
                    c38431.L$8 = documentReferenceDocument2;
                    c38431.Z$0 = zAreEqual;
                    c38431.label = 4;
                    objAwait2 = TasksKt.await(task8, c38431);
                    if (objAwait2 != coroutine_suspended) {
                        context5 = context4;
                        map2 = map;
                        documentReference2 = documentReference;
                        firebaseUser = currentUser;
                        documentReference3 = documentReferenceDocument2;
                        obj6 = objAwait2;
                        str4 = lowerCase;
                        z = zAreEqual;
                        map3 = mapMapOf;
                        documentSnapshot = (DocumentSnapshot) obj6;
                        data2 = documentSnapshot.getData();
                        if (z) {
                            if (data2 != null) {
                                obj7 = data2.get(NotificationCompat.CATEGORY_STATUS);
                            } else {
                                obj7 = null;
                            }
                            documentSnapshot2 = documentSnapshot;
                            String str23 = str18;
                            zAreEqual2 = Intrinsics.areEqual(obj7, str23);
                            str18 = str23;
                            str5 = str19;
                            if (zAreEqual2) {
                                map4 = map3;
                                if (Intrinsics.areEqual(data2.get(str5), firebaseUser.getUid())) {
                                }
                                Result.Companion companion9 = Result.INSTANCE;
                                FirebaseCloudSync firebaseCloudSync7 = this;
                                Task<String> token7 = FirebaseMessaging.getInstance().getToken();
                                str6 = "_";
                                Intrinsics.checkNotNullExpressionValue(token7, "getToken(...)");
                                c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                c38431.L$1 = firebaseUser;
                                c38431.L$2 = str4;
                                c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                c38431.L$5 = str;
                                c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                                c38431.L$7 = str3;
                                c38431.L$8 = documentReference3;
                                c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                                c38431.L$10 = data2;
                                c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                                c38431.Z$0 = z;
                                c38431.I$0 = i;
                                c38431.I$1 = 0;
                                c38431.label = 5;
                                objAwait3 = TasksKt.await(token7, c38431);
                                if (objAwait3 != coroutine_suspended) {
                                    str7 = str4;
                                    firebaseUser2 = firebaseUser;
                                    map5 = map4;
                                    documentSnapshot3 = documentSnapshot2;
                                    documentReference4 = documentReference3;
                                    i2 = i;
                                    map6 = data2;
                                    objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                                    i3 = i2;
                                    z2 = z;
                                    str8 = str7;
                                    str9 = str;
                                    map7 = map5;
                                    documentReference5 = documentReference4;
                                    documentSnapshot4 = documentSnapshot3;
                                    firebaseUser3 = firebaseUser2;
                                    str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                                    if (str10 == null) {
                                        str11 = str20;
                                    } else {
                                        str11 = str10;
                                    }
                                    Pair[] pairArr11 = new Pair[9];
                                    pairArr11[0] = TuplesKt.m921to(str17, str3);
                                    pairArr11[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                                    pairArr11[2] = TuplesKt.m921to("email", str8);
                                    str12 = str5;
                                    pairArr11[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                                    pairArr11[4] = TuplesKt.m921to("fcmToken", str11);
                                    if (z2) {
                                        str13 = str18;
                                    } else {
                                        str13 = str16;
                                    }
                                    pairArr11[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                                    if (i3 != 0) {
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                    }
                                    pairArr11[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                                    pairArr11[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                                    if (map6 != null) {
                                        objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                    } else {
                                        objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                    }
                                    pairArr11[8] = TuplesKt.m921to("createdAt", objBoxLong);
                                    task2 = documentReference5.set(MapsKt.mapOf(pairArr11), SetOptions.merge());
                                    Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                                    c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                    c38431.L$1 = firebaseUser3;
                                    c38431.L$2 = str8;
                                    c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                    c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                    c38431.L$5 = str9;
                                    c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                                    c38431.L$7 = str3;
                                    c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                                    c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                                    c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                                    c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                                    c38431.Z$0 = z2;
                                    c38431.I$0 = i3;
                                    c38431.label = 6;
                                    if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                        map8 = map7;
                                        str14 = str11;
                                        firebaseUser4 = firebaseUser3;
                                        str15 = str9;
                                        z4 = z2;
                                        map9 = map6;
                                        documentSnapshot5 = documentSnapshot4;
                                        if (z4) {
                                        }
                                        return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                                    }
                                }
                            } else {
                                map4 = map3;
                            }
                            i = 0;
                            Result.Companion companion10 = Result.INSTANCE;
                            FirebaseCloudSync firebaseCloudSync8 = this;
                            Task<String> token8 = FirebaseMessaging.getInstance().getToken();
                            str6 = "_";
                            Intrinsics.checkNotNullExpressionValue(token8, "getToken(...)");
                            c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                            c38431.L$1 = firebaseUser;
                            c38431.L$2 = str4;
                            c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                            c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                            c38431.L$5 = str;
                            c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                            c38431.L$7 = str3;
                            c38431.L$8 = documentReference3;
                            c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                            c38431.L$10 = data2;
                            c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                            c38431.Z$0 = z;
                            c38431.I$0 = i;
                            c38431.I$1 = 0;
                            c38431.label = 5;
                            objAwait3 = TasksKt.await(token8, c38431);
                            if (objAwait3 != coroutine_suspended) {
                                str7 = str4;
                                firebaseUser2 = firebaseUser;
                                map5 = map4;
                                documentSnapshot3 = documentSnapshot2;
                                documentReference4 = documentReference3;
                                i2 = i;
                                map6 = data2;
                                objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                                i3 = i2;
                                z2 = z;
                                str8 = str7;
                                str9 = str;
                                map7 = map5;
                                documentReference5 = documentReference4;
                                documentSnapshot4 = documentSnapshot3;
                                firebaseUser3 = firebaseUser2;
                                str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                                if (str10 == null) {
                                    str11 = str20;
                                } else {
                                    str11 = str10;
                                }
                                Pair[] pairArr12 = new Pair[9];
                                pairArr12[0] = TuplesKt.m921to(str17, str3);
                                pairArr12[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                                pairArr12[2] = TuplesKt.m921to("email", str8);
                                str12 = str5;
                                pairArr12[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                                pairArr12[4] = TuplesKt.m921to("fcmToken", str11);
                                if (z2) {
                                    str13 = str18;
                                } else {
                                    str13 = str16;
                                }
                                pairArr12[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                                if (i3 != 0) {
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                                pairArr12[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                                pairArr12[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                                if (map6 != null) {
                                    objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                } else {
                                    objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                }
                                pairArr12[8] = TuplesKt.m921to("createdAt", objBoxLong);
                                task2 = documentReference5.set(MapsKt.mapOf(pairArr12), SetOptions.merge());
                                Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                                c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                c38431.L$1 = firebaseUser3;
                                c38431.L$2 = str8;
                                c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                c38431.L$5 = str9;
                                c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                                c38431.L$7 = str3;
                                c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                                c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                                c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                                c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                                c38431.Z$0 = z2;
                                c38431.I$0 = i3;
                                c38431.label = 6;
                                if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                    map8 = map7;
                                    str14 = str11;
                                    firebaseUser4 = firebaseUser3;
                                    str15 = str9;
                                    z4 = z2;
                                    map9 = map6;
                                    documentSnapshot5 = documentSnapshot4;
                                    if (z4) {
                                    }
                                    return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                                }
                            }
                        } else {
                            documentSnapshot2 = documentSnapshot;
                            str5 = str19;
                            map4 = map3;
                        }
                        i = 1;
                        Result.Companion companion11 = Result.INSTANCE;
                        FirebaseCloudSync firebaseCloudSync9 = this;
                        Task<String> token9 = FirebaseMessaging.getInstance().getToken();
                        str6 = "_";
                        Intrinsics.checkNotNullExpressionValue(token9, "getToken(...)");
                        c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                        c38431.L$1 = firebaseUser;
                        c38431.L$2 = str4;
                        c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                        c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                        c38431.L$5 = str;
                        c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                        c38431.L$7 = str3;
                        c38431.L$8 = documentReference3;
                        c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                        c38431.L$10 = data2;
                        c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                        c38431.Z$0 = z;
                        c38431.I$0 = i;
                        c38431.I$1 = 0;
                        c38431.label = 5;
                        objAwait3 = TasksKt.await(token9, c38431);
                        if (objAwait3 != coroutine_suspended) {
                            str7 = str4;
                            firebaseUser2 = firebaseUser;
                            map5 = map4;
                            documentSnapshot3 = documentSnapshot2;
                            documentReference4 = documentReference3;
                            i2 = i;
                            map6 = data2;
                            objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                            i3 = i2;
                            z2 = z;
                            str8 = str7;
                            str9 = str;
                            map7 = map5;
                            documentReference5 = documentReference4;
                            documentSnapshot4 = documentSnapshot3;
                            firebaseUser3 = firebaseUser2;
                            str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                            if (str10 == null) {
                                str11 = str20;
                            } else {
                                str11 = str10;
                            }
                            Pair[] pairArr13 = new Pair[9];
                            pairArr13[0] = TuplesKt.m921to(str17, str3);
                            pairArr13[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                            pairArr13[2] = TuplesKt.m921to("email", str8);
                            str12 = str5;
                            pairArr13[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                            pairArr13[4] = TuplesKt.m921to("fcmToken", str11);
                            if (z2) {
                                str13 = str18;
                            } else {
                                str13 = str16;
                            }
                            pairArr13[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                            if (i3 != 0) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            pairArr13[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                            pairArr13[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                            if (map6 != null) {
                                objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                            } else {
                                objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                            }
                            pairArr13[8] = TuplesKt.m921to("createdAt", objBoxLong);
                            task2 = documentReference5.set(MapsKt.mapOf(pairArr13), SetOptions.merge());
                            Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                            c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                            c38431.L$1 = firebaseUser3;
                            c38431.L$2 = str8;
                            c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                            c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                            c38431.L$5 = str9;
                            c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                            c38431.L$7 = str3;
                            c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                            c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                            c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                            c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                            c38431.Z$0 = z2;
                            c38431.I$0 = i3;
                            c38431.label = 6;
                            if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                map8 = map7;
                                str14 = str11;
                                firebaseUser4 = firebaseUser3;
                                str15 = str9;
                                z4 = z2;
                                map9 = map6;
                                documentSnapshot5 = documentSnapshot4;
                                if (z4) {
                                }
                                return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                            }
                        }
                    }
                }
                return coroutine_suspended;
            case 3:
                boolean z6 = c38431.Z$0;
                Map map10 = (Map) c38431.L$6;
                String str24 = (String) c38431.L$5;
                Map<String, Object> map11 = (Map) c38431.L$4;
                DocumentReference documentReference7 = (DocumentReference) c38431.L$3;
                String str25 = (String) c38431.L$2;
                FirebaseUser firebaseUser7 = (FirebaseUser) c38431.L$1;
                context4 = (Context) c38431.L$0;
                ResultKt.throwOnFailure(obj8);
                str16 = "PENDING";
                map = map11;
                zAreEqual = z6;
                documentReferenceDocument = documentReference7;
                str20 = "";
                mapMapOf = map10;
                currentUser = firebaseUser7;
                str = str24;
                lowerCase = str25;
                string5 = Settings.Secure.getString(context4.getContentResolver(), "android_id");
                if (string5 == null) {
                    string5 = str20;
                }
                str2 = string5;
                if (StringsKt.isBlank(str2)) {
                    String uid4 = currentUser.getUid();
                    Intrinsics.checkNotNullExpressionValue(uid4, "getUid(...)");
                    str2 = "unknown-" + StringsKt.take(uid4, 8);
                }
                str3 = str2;
                documentReference = documentReferenceDocument;
                documentReferenceDocument2 = firestore().collection("devices").document(currentUser.getUid() + "_" + str3);
                Intrinsics.checkNotNullExpressionValue(documentReferenceDocument2, "document(...)");
                Task<DocumentSnapshot> task9 = documentReferenceDocument2.get();
                Intrinsics.checkNotNullExpressionValue(task9, "get(...)");
                c38431.L$0 = SpillingKt.nullOutSpilledVariable(context4);
                c38431.L$1 = currentUser;
                c38431.L$2 = lowerCase;
                c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference);
                c38431.L$4 = SpillingKt.nullOutSpilledVariable(map);
                c38431.L$5 = str;
                c38431.L$6 = SpillingKt.nullOutSpilledVariable(mapMapOf);
                c38431.L$7 = str3;
                c38431.L$8 = documentReferenceDocument2;
                c38431.Z$0 = zAreEqual;
                c38431.label = 4;
                objAwait2 = TasksKt.await(task9, c38431);
                if (objAwait2 != coroutine_suspended) {
                    context5 = context4;
                    map2 = map;
                    documentReference2 = documentReference;
                    firebaseUser = currentUser;
                    documentReference3 = documentReferenceDocument2;
                    obj6 = objAwait2;
                    str4 = lowerCase;
                    z = zAreEqual;
                    map3 = mapMapOf;
                    documentSnapshot = (DocumentSnapshot) obj6;
                    data2 = documentSnapshot.getData();
                    if (z) {
                        if (data2 != null) {
                            obj7 = data2.get(NotificationCompat.CATEGORY_STATUS);
                        } else {
                            obj7 = null;
                        }
                        documentSnapshot2 = documentSnapshot;
                        String str26 = str18;
                        zAreEqual2 = Intrinsics.areEqual(obj7, str26);
                        str18 = str26;
                        str5 = str19;
                        if (zAreEqual2) {
                            map4 = map3;
                            if (Intrinsics.areEqual(data2.get(str5), firebaseUser.getUid())) {
                            }
                            Result.Companion companion12 = Result.INSTANCE;
                            FirebaseCloudSync firebaseCloudSync10 = this;
                            Task<String> token10 = FirebaseMessaging.getInstance().getToken();
                            str6 = "_";
                            Intrinsics.checkNotNullExpressionValue(token10, "getToken(...)");
                            c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                            c38431.L$1 = firebaseUser;
                            c38431.L$2 = str4;
                            c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                            c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                            c38431.L$5 = str;
                            c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                            c38431.L$7 = str3;
                            c38431.L$8 = documentReference3;
                            c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                            c38431.L$10 = data2;
                            c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                            c38431.Z$0 = z;
                            c38431.I$0 = i;
                            c38431.I$1 = 0;
                            c38431.label = 5;
                            objAwait3 = TasksKt.await(token10, c38431);
                            if (objAwait3 != coroutine_suspended) {
                                str7 = str4;
                                firebaseUser2 = firebaseUser;
                                map5 = map4;
                                documentSnapshot3 = documentSnapshot2;
                                documentReference4 = documentReference3;
                                i2 = i;
                                map6 = data2;
                                objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                                i3 = i2;
                                z2 = z;
                                str8 = str7;
                                str9 = str;
                                map7 = map5;
                                documentReference5 = documentReference4;
                                documentSnapshot4 = documentSnapshot3;
                                firebaseUser3 = firebaseUser2;
                                str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                                if (str10 == null) {
                                    str11 = str20;
                                } else {
                                    str11 = str10;
                                }
                                Pair[] pairArr14 = new Pair[9];
                                pairArr14[0] = TuplesKt.m921to(str17, str3);
                                pairArr14[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                                pairArr14[2] = TuplesKt.m921to("email", str8);
                                str12 = str5;
                                pairArr14[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                                pairArr14[4] = TuplesKt.m921to("fcmToken", str11);
                                if (z2) {
                                    str13 = str18;
                                } else {
                                    str13 = str16;
                                }
                                pairArr14[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                                if (i3 != 0) {
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                                pairArr14[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                                pairArr14[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                                if (map6 != null) {
                                    objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                } else {
                                    objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                                }
                                pairArr14[8] = TuplesKt.m921to("createdAt", objBoxLong);
                                task2 = documentReference5.set(MapsKt.mapOf(pairArr14), SetOptions.merge());
                                Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                                c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                c38431.L$1 = firebaseUser3;
                                c38431.L$2 = str8;
                                c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                                c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                                c38431.L$5 = str9;
                                c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                                c38431.L$7 = str3;
                                c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                                c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                                c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                                c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                                c38431.Z$0 = z2;
                                c38431.I$0 = i3;
                                c38431.label = 6;
                                if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                    map8 = map7;
                                    str14 = str11;
                                    firebaseUser4 = firebaseUser3;
                                    str15 = str9;
                                    z4 = z2;
                                    map9 = map6;
                                    documentSnapshot5 = documentSnapshot4;
                                    if (z4) {
                                    }
                                    return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                                }
                            }
                        } else {
                            map4 = map3;
                        }
                        i = 0;
                        Result.Companion companion13 = Result.INSTANCE;
                        FirebaseCloudSync firebaseCloudSync11 = this;
                        Task<String> token11 = FirebaseMessaging.getInstance().getToken();
                        str6 = "_";
                        Intrinsics.checkNotNullExpressionValue(token11, "getToken(...)");
                        c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                        c38431.L$1 = firebaseUser;
                        c38431.L$2 = str4;
                        c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                        c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                        c38431.L$5 = str;
                        c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                        c38431.L$7 = str3;
                        c38431.L$8 = documentReference3;
                        c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                        c38431.L$10 = data2;
                        c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                        c38431.Z$0 = z;
                        c38431.I$0 = i;
                        c38431.I$1 = 0;
                        c38431.label = 5;
                        objAwait3 = TasksKt.await(token11, c38431);
                        if (objAwait3 != coroutine_suspended) {
                            str7 = str4;
                            firebaseUser2 = firebaseUser;
                            map5 = map4;
                            documentSnapshot3 = documentSnapshot2;
                            documentReference4 = documentReference3;
                            i2 = i;
                            map6 = data2;
                            objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                            i3 = i2;
                            z2 = z;
                            str8 = str7;
                            str9 = str;
                            map7 = map5;
                            documentReference5 = documentReference4;
                            documentSnapshot4 = documentSnapshot3;
                            firebaseUser3 = firebaseUser2;
                            str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                            if (str10 == null) {
                                str11 = str20;
                            } else {
                                str11 = str10;
                            }
                            Pair[] pairArr15 = new Pair[9];
                            pairArr15[0] = TuplesKt.m921to(str17, str3);
                            pairArr15[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                            pairArr15[2] = TuplesKt.m921to("email", str8);
                            str12 = str5;
                            pairArr15[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                            pairArr15[4] = TuplesKt.m921to("fcmToken", str11);
                            if (z2) {
                                str13 = str18;
                            } else {
                                str13 = str16;
                            }
                            pairArr15[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                            if (i3 != 0) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            pairArr15[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                            pairArr15[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                            if (map6 != null) {
                                objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                            } else {
                                objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                            }
                            pairArr15[8] = TuplesKt.m921to("createdAt", objBoxLong);
                            task2 = documentReference5.set(MapsKt.mapOf(pairArr15), SetOptions.merge());
                            Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                            c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                            c38431.L$1 = firebaseUser3;
                            c38431.L$2 = str8;
                            c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                            c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                            c38431.L$5 = str9;
                            c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                            c38431.L$7 = str3;
                            c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                            c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                            c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                            c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                            c38431.Z$0 = z2;
                            c38431.I$0 = i3;
                            c38431.label = 6;
                            if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                map8 = map7;
                                str14 = str11;
                                firebaseUser4 = firebaseUser3;
                                str15 = str9;
                                z4 = z2;
                                map9 = map6;
                                documentSnapshot5 = documentSnapshot4;
                                if (z4) {
                                }
                                return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                            }
                        }
                    } else {
                        documentSnapshot2 = documentSnapshot;
                        str5 = str19;
                        map4 = map3;
                    }
                    i = 1;
                    Result.Companion companion14 = Result.INSTANCE;
                    FirebaseCloudSync firebaseCloudSync12 = this;
                    Task<String> token12 = FirebaseMessaging.getInstance().getToken();
                    str6 = "_";
                    Intrinsics.checkNotNullExpressionValue(token12, "getToken(...)");
                    c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                    c38431.L$1 = firebaseUser;
                    c38431.L$2 = str4;
                    c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                    c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                    c38431.L$5 = str;
                    c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                    c38431.L$7 = str3;
                    c38431.L$8 = documentReference3;
                    c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                    c38431.L$10 = data2;
                    c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                    c38431.Z$0 = z;
                    c38431.I$0 = i;
                    c38431.I$1 = 0;
                    c38431.label = 5;
                    objAwait3 = TasksKt.await(token12, c38431);
                    if (objAwait3 != coroutine_suspended) {
                        str7 = str4;
                        firebaseUser2 = firebaseUser;
                        map5 = map4;
                        documentSnapshot3 = documentSnapshot2;
                        documentReference4 = documentReference3;
                        i2 = i;
                        map6 = data2;
                        objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                        i3 = i2;
                        z2 = z;
                        str8 = str7;
                        str9 = str;
                        map7 = map5;
                        documentReference5 = documentReference4;
                        documentSnapshot4 = documentSnapshot3;
                        firebaseUser3 = firebaseUser2;
                        str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                        if (str10 == null) {
                            str11 = str20;
                        } else {
                            str11 = str10;
                        }
                        Pair[] pairArr16 = new Pair[9];
                        pairArr16[0] = TuplesKt.m921to(str17, str3);
                        pairArr16[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                        pairArr16[2] = TuplesKt.m921to("email", str8);
                        str12 = str5;
                        pairArr16[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                        pairArr16[4] = TuplesKt.m921to("fcmToken", str11);
                        if (z2) {
                            str13 = str18;
                        } else {
                            str13 = str16;
                        }
                        pairArr16[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                        if (i3 != 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        pairArr16[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                        pairArr16[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                        if (map6 != null) {
                            objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                        } else {
                            objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                        }
                        pairArr16[8] = TuplesKt.m921to("createdAt", objBoxLong);
                        task2 = documentReference5.set(MapsKt.mapOf(pairArr16), SetOptions.merge());
                        Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                        c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                        c38431.L$1 = firebaseUser3;
                        c38431.L$2 = str8;
                        c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                        c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                        c38431.L$5 = str9;
                        c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                        c38431.L$7 = str3;
                        c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                        c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                        c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                        c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                        c38431.Z$0 = z2;
                        c38431.I$0 = i3;
                        c38431.label = 6;
                        if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                            map8 = map7;
                            str14 = str11;
                            firebaseUser4 = firebaseUser3;
                            str15 = str9;
                            z4 = z2;
                            map9 = map6;
                            documentSnapshot5 = documentSnapshot4;
                            if (z4) {
                            }
                            return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                        }
                    }
                }
                return coroutine_suspended;
            case 4:
                boolean z7 = c38431.Z$0;
                documentReference3 = (DocumentReference) c38431.L$8;
                String str27 = (String) c38431.L$7;
                map3 = (Map) c38431.L$6;
                String str28 = (String) c38431.L$5;
                Map<String, Object> map12 = (Map) c38431.L$4;
                DocumentReference documentReference8 = (DocumentReference) c38431.L$3;
                String str29 = (String) c38431.L$2;
                FirebaseUser firebaseUser8 = (FirebaseUser) c38431.L$1;
                Context context10 = (Context) c38431.L$0;
                ResultKt.throwOnFailure(obj8);
                context5 = context10;
                obj6 = obj8;
                map2 = map12;
                documentReference2 = documentReference8;
                str4 = str29;
                str17 = "deviceId";
                str20 = "";
                str18 = "APPROVED";
                str19 = "userId";
                str = str28;
                str3 = str27;
                z = z7;
                str16 = "PENDING";
                firebaseUser = firebaseUser8;
                documentSnapshot = (DocumentSnapshot) obj6;
                data2 = documentSnapshot.getData();
                if (z) {
                    if (data2 != null) {
                        obj7 = data2.get(NotificationCompat.CATEGORY_STATUS);
                    } else {
                        obj7 = null;
                    }
                    documentSnapshot2 = documentSnapshot;
                    String str210 = str18;
                    zAreEqual2 = Intrinsics.areEqual(obj7, str210);
                    str18 = str210;
                    str5 = str19;
                    if (zAreEqual2) {
                        map4 = map3;
                        if (Intrinsics.areEqual(data2.get(str5), firebaseUser.getUid())) {
                        }
                        Result.Companion companion15 = Result.INSTANCE;
                        FirebaseCloudSync firebaseCloudSync13 = this;
                        Task<String> token13 = FirebaseMessaging.getInstance().getToken();
                        str6 = "_";
                        Intrinsics.checkNotNullExpressionValue(token13, "getToken(...)");
                        c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                        c38431.L$1 = firebaseUser;
                        c38431.L$2 = str4;
                        c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                        c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                        c38431.L$5 = str;
                        c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                        c38431.L$7 = str3;
                        c38431.L$8 = documentReference3;
                        c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                        c38431.L$10 = data2;
                        c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                        c38431.Z$0 = z;
                        c38431.I$0 = i;
                        c38431.I$1 = 0;
                        c38431.label = 5;
                        objAwait3 = TasksKt.await(token13, c38431);
                        if (objAwait3 != coroutine_suspended) {
                            str7 = str4;
                            firebaseUser2 = firebaseUser;
                            map5 = map4;
                            documentSnapshot3 = documentSnapshot2;
                            documentReference4 = documentReference3;
                            i2 = i;
                            map6 = data2;
                            objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                            i3 = i2;
                            z2 = z;
                            str8 = str7;
                            str9 = str;
                            map7 = map5;
                            documentReference5 = documentReference4;
                            documentSnapshot4 = documentSnapshot3;
                            firebaseUser3 = firebaseUser2;
                            str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                            if (str10 == null) {
                                str11 = str20;
                            } else {
                                str11 = str10;
                            }
                            Pair[] pairArr17 = new Pair[9];
                            pairArr17[0] = TuplesKt.m921to(str17, str3);
                            pairArr17[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                            pairArr17[2] = TuplesKt.m921to("email", str8);
                            str12 = str5;
                            pairArr17[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                            pairArr17[4] = TuplesKt.m921to("fcmToken", str11);
                            if (z2) {
                                str13 = str18;
                            } else {
                                str13 = str16;
                            }
                            pairArr17[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                            if (i3 != 0) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            pairArr17[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                            pairArr17[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                            if (map6 != null) {
                                objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                            } else {
                                objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                            }
                            pairArr17[8] = TuplesKt.m921to("createdAt", objBoxLong);
                            task2 = documentReference5.set(MapsKt.mapOf(pairArr17), SetOptions.merge());
                            Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                            c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                            c38431.L$1 = firebaseUser3;
                            c38431.L$2 = str8;
                            c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                            c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                            c38431.L$5 = str9;
                            c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                            c38431.L$7 = str3;
                            c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                            c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                            c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                            c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                            c38431.Z$0 = z2;
                            c38431.I$0 = i3;
                            c38431.label = 6;
                            if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                                map8 = map7;
                                str14 = str11;
                                firebaseUser4 = firebaseUser3;
                                str15 = str9;
                                z4 = z2;
                                map9 = map6;
                                documentSnapshot5 = documentSnapshot4;
                                if (z4) {
                                }
                                return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                            }
                        }
                        return coroutine_suspended;
                    }
                    map4 = map3;
                    i = 0;
                    Result.Companion companion16 = Result.INSTANCE;
                    FirebaseCloudSync firebaseCloudSync14 = this;
                    Task<String> token14 = FirebaseMessaging.getInstance().getToken();
                    str6 = "_";
                    Intrinsics.checkNotNullExpressionValue(token14, "getToken(...)");
                    c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                    c38431.L$1 = firebaseUser;
                    c38431.L$2 = str4;
                    c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                    c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                    c38431.L$5 = str;
                    c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                    c38431.L$7 = str3;
                    c38431.L$8 = documentReference3;
                    c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                    c38431.L$10 = data2;
                    c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                    c38431.Z$0 = z;
                    c38431.I$0 = i;
                    c38431.I$1 = 0;
                    c38431.label = 5;
                    objAwait3 = TasksKt.await(token14, c38431);
                    if (objAwait3 != coroutine_suspended) {
                        str7 = str4;
                        firebaseUser2 = firebaseUser;
                        map5 = map4;
                        documentSnapshot3 = documentSnapshot2;
                        documentReference4 = documentReference3;
                        i2 = i;
                        map6 = data2;
                        objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                        i3 = i2;
                        z2 = z;
                        str8 = str7;
                        str9 = str;
                        map7 = map5;
                        documentReference5 = documentReference4;
                        documentSnapshot4 = documentSnapshot3;
                        firebaseUser3 = firebaseUser2;
                        str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                        if (str10 == null) {
                            str11 = str20;
                        } else {
                            str11 = str10;
                        }
                        Pair[] pairArr18 = new Pair[9];
                        pairArr18[0] = TuplesKt.m921to(str17, str3);
                        pairArr18[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                        pairArr18[2] = TuplesKt.m921to("email", str8);
                        str12 = str5;
                        pairArr18[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                        pairArr18[4] = TuplesKt.m921to("fcmToken", str11);
                        if (z2) {
                            str13 = str18;
                        } else {
                            str13 = str16;
                        }
                        pairArr18[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                        if (i3 != 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        pairArr18[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                        pairArr18[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                        if (map6 != null) {
                            objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                        } else {
                            objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                        }
                        pairArr18[8] = TuplesKt.m921to("createdAt", objBoxLong);
                        task2 = documentReference5.set(MapsKt.mapOf(pairArr18), SetOptions.merge());
                        Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                        c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                        c38431.L$1 = firebaseUser3;
                        c38431.L$2 = str8;
                        c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                        c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                        c38431.L$5 = str9;
                        c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                        c38431.L$7 = str3;
                        c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                        c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                        c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                        c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                        c38431.Z$0 = z2;
                        c38431.I$0 = i3;
                        c38431.label = 6;
                        if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                            map8 = map7;
                            str14 = str11;
                            firebaseUser4 = firebaseUser3;
                            str15 = str9;
                            z4 = z2;
                            map9 = map6;
                            documentSnapshot5 = documentSnapshot4;
                            if (z4) {
                            }
                            return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                        }
                    }
                    return coroutine_suspended;
                }
                documentSnapshot2 = documentSnapshot;
                str5 = str19;
                map4 = map3;
                i = 1;
                Result.Companion companion17 = Result.INSTANCE;
                FirebaseCloudSync firebaseCloudSync15 = this;
                Task<String> token15 = FirebaseMessaging.getInstance().getToken();
                str6 = "_";
                Intrinsics.checkNotNullExpressionValue(token15, "getToken(...)");
                c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                c38431.L$1 = firebaseUser;
                c38431.L$2 = str4;
                c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                c38431.L$5 = str;
                c38431.L$6 = SpillingKt.nullOutSpilledVariable(map4);
                c38431.L$7 = str3;
                c38431.L$8 = documentReference3;
                c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot2);
                c38431.L$10 = data2;
                c38431.L$11 = SpillingKt.nullOutSpilledVariable(this);
                c38431.Z$0 = z;
                c38431.I$0 = i;
                c38431.I$1 = 0;
                c38431.label = 5;
                objAwait3 = TasksKt.await(token15, c38431);
                if (objAwait3 != coroutine_suspended) {
                    str7 = str4;
                    firebaseUser2 = firebaseUser;
                    map5 = map4;
                    documentSnapshot3 = documentSnapshot2;
                    documentReference4 = documentReference3;
                    i2 = i;
                    map6 = data2;
                    objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                    i3 = i2;
                    z2 = z;
                    str8 = str7;
                    str9 = str;
                    map7 = map5;
                    documentReference5 = documentReference4;
                    documentSnapshot4 = documentSnapshot3;
                    firebaseUser3 = firebaseUser2;
                    str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                    if (str10 == null) {
                        str11 = str20;
                    } else {
                        str11 = str10;
                    }
                    Pair[] pairArr19 = new Pair[9];
                    pairArr19[0] = TuplesKt.m921to(str17, str3);
                    pairArr19[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                    pairArr19[2] = TuplesKt.m921to("email", str8);
                    str12 = str5;
                    pairArr19[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                    pairArr19[4] = TuplesKt.m921to("fcmToken", str11);
                    if (z2) {
                        str13 = str18;
                    } else {
                        str13 = str16;
                    }
                    pairArr19[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                    if (i3 != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    pairArr19[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                    pairArr19[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                    if (map6 != null) {
                        objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                    } else {
                        objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                    }
                    pairArr19[8] = TuplesKt.m921to("createdAt", objBoxLong);
                    task2 = documentReference5.set(MapsKt.mapOf(pairArr19), SetOptions.merge());
                    Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                    c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                    c38431.L$1 = firebaseUser3;
                    c38431.L$2 = str8;
                    c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                    c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                    c38431.L$5 = str9;
                    c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                    c38431.L$7 = str3;
                    c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                    c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                    c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                    c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                    c38431.Z$0 = z2;
                    c38431.I$0 = i3;
                    c38431.label = 6;
                    if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                        map8 = map7;
                        str14 = str11;
                        firebaseUser4 = firebaseUser3;
                        str15 = str9;
                        z4 = z2;
                        map9 = map6;
                        documentSnapshot5 = documentSnapshot4;
                        if (z4) {
                        }
                        return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                    }
                }
                return coroutine_suspended;
            case 5:
                int i4 = c38431.I$1;
                i2 = c38431.I$0;
                z = c38431.Z$0;
                map6 = (Map) c38431.L$10;
                DocumentSnapshot documentSnapshot6 = (DocumentSnapshot) c38431.L$9;
                documentReference4 = (DocumentReference) c38431.L$8;
                String str30 = (String) c38431.L$7;
                Map map13 = (Map) c38431.L$6;
                String str31 = (String) c38431.L$5;
                map2 = (Map) c38431.L$4;
                documentReference2 = (DocumentReference) c38431.L$3;
                str7 = (String) c38431.L$2;
                firebaseUser2 = (FirebaseUser) c38431.L$1;
                context5 = (Context) c38431.L$0;
                try {
                    ResultKt.throwOnFailure(obj8);
                    objAwait3 = obj8;
                    documentSnapshot3 = documentSnapshot6;
                    str16 = "PENDING";
                    str20 = "";
                    str5 = "userId";
                    map5 = map13;
                    str18 = "APPROVED";
                    str3 = str30;
                    str17 = "deviceId";
                    str = str31;
                    str6 = "_";
                    objM7781constructorimpl = Result.m7781constructorimpl((String) objAwait3);
                    break;
                } catch (Throwable th4) {
                    th = th4;
                    str20 = "";
                    str5 = "userId";
                    documentSnapshot3 = documentSnapshot6;
                    str16 = "PENDING";
                    map5 = map13;
                    str18 = "APPROVED";
                    str3 = str30;
                    str17 = "deviceId";
                    str = str31;
                    str6 = "_";
                    Result.Companion companion18 = Result.INSTANCE;
                    objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                    i3 = i2;
                    z2 = z;
                    str8 = str7;
                    str9 = str;
                    map7 = map5;
                    documentReference5 = documentReference4;
                    documentSnapshot4 = documentSnapshot3;
                    firebaseUser3 = firebaseUser2;
                    str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                    if (str10 == null) {
                        str11 = str20;
                    } else {
                        str11 = str10;
                    }
                    Pair[] pairArr110 = new Pair[9];
                    pairArr110[0] = TuplesKt.m921to(str17, str3);
                    pairArr110[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                    pairArr110[2] = TuplesKt.m921to("email", str8);
                    str12 = str5;
                    pairArr110[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                    pairArr110[4] = TuplesKt.m921to("fcmToken", str11);
                    if (z2) {
                        str13 = str18;
                    } else {
                        str13 = str16;
                    }
                    pairArr110[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                    if (i3 != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    pairArr110[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                    pairArr110[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                    if (map6 != null) {
                        objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                    } else {
                        objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                    }
                    pairArr110[8] = TuplesKt.m921to("createdAt", objBoxLong);
                    task2 = documentReference5.set(MapsKt.mapOf(pairArr110), SetOptions.merge());
                    Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                    c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                    c38431.L$1 = firebaseUser3;
                    c38431.L$2 = str8;
                    c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                    c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                    c38431.L$5 = str9;
                    c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                    c38431.L$7 = str3;
                    c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                    c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                    c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                    c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                    c38431.Z$0 = z2;
                    c38431.I$0 = i3;
                    c38431.label = 6;
                    if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                        map8 = map7;
                        str14 = str11;
                        firebaseUser4 = firebaseUser3;
                        str15 = str9;
                        z4 = z2;
                        map9 = map6;
                        documentSnapshot5 = documentSnapshot4;
                        if (z4) {
                        }
                        return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                    }
                    return coroutine_suspended;
                }
                i3 = i2;
                z2 = z;
                str8 = str7;
                str9 = str;
                map7 = map5;
                documentReference5 = documentReference4;
                documentSnapshot4 = documentSnapshot3;
                firebaseUser3 = firebaseUser2;
                str10 = (String) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
                if (str10 == null) {
                    str11 = str20;
                } else {
                    str11 = str10;
                }
                Pair[] pairArr111 = new Pair[9];
                pairArr111[0] = TuplesKt.m921to(str17, str3);
                pairArr111[1] = TuplesKt.m921to(str5, firebaseUser3.getUid());
                pairArr111[2] = TuplesKt.m921to("email", str8);
                str12 = str5;
                pairArr111[3] = TuplesKt.m921to("deviceName", "Android " + Build.MODEL);
                pairArr111[4] = TuplesKt.m921to("fcmToken", str11);
                if (z2) {
                    str13 = str18;
                } else {
                    str13 = str16;
                }
                pairArr111[5] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, str13);
                if (i3 != 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                pairArr111[6] = TuplesKt.m921to("approved", Boxing.boxBoolean(z3));
                pairArr111[7] = TuplesKt.m921to("lastSeenAt", Boxing.boxLong(System.currentTimeMillis()));
                if (map6 != null) {
                    objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                } else {
                    objBoxLong = Boxing.boxLong(System.currentTimeMillis());
                }
                pairArr111[8] = TuplesKt.m921to("createdAt", objBoxLong);
                task2 = documentReference5.set(MapsKt.mapOf(pairArr111), SetOptions.merge());
                Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
                c38431.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                c38431.L$1 = firebaseUser3;
                c38431.L$2 = str8;
                c38431.L$3 = SpillingKt.nullOutSpilledVariable(documentReference2);
                c38431.L$4 = SpillingKt.nullOutSpilledVariable(map2);
                c38431.L$5 = str9;
                c38431.L$6 = SpillingKt.nullOutSpilledVariable(map7);
                c38431.L$7 = str3;
                c38431.L$8 = SpillingKt.nullOutSpilledVariable(documentReference5);
                c38431.L$9 = SpillingKt.nullOutSpilledVariable(documentSnapshot4);
                c38431.L$10 = SpillingKt.nullOutSpilledVariable(map6);
                c38431.L$11 = SpillingKt.nullOutSpilledVariable(str11);
                c38431.Z$0 = z2;
                c38431.I$0 = i3;
                c38431.label = 6;
                if (TasksKt.await(task2, c38431) != coroutine_suspended) {
                    map8 = map7;
                    str14 = str11;
                    firebaseUser4 = firebaseUser3;
                    str15 = str9;
                    z4 = z2;
                    map9 = map6;
                    documentSnapshot5 = documentSnapshot4;
                    if (z4) {
                    }
                    return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
                }
                return coroutine_suspended;
            case 6:
                int i5 = c38431.I$0;
                z4 = c38431.Z$0;
                String str32 = (String) c38431.L$11;
                map9 = (Map) c38431.L$10;
                documentSnapshot5 = (DocumentSnapshot) c38431.L$9;
                DocumentReference documentReference9 = (DocumentReference) c38431.L$8;
                String str33 = (String) c38431.L$7;
                map8 = (Map) c38431.L$6;
                String str34 = (String) c38431.L$5;
                Map<String, Object> map14 = (Map) c38431.L$4;
                DocumentReference documentReference10 = (DocumentReference) c38431.L$3;
                String str35 = (String) c38431.L$2;
                FirebaseUser firebaseUser9 = (FirebaseUser) c38431.L$1;
                Context context11 = (Context) c38431.L$0;
                ResultKt.throwOnFailure(obj8);
                context5 = context11;
                str6 = "_";
                str12 = "userId";
                str15 = str34;
                map2 = map14;
                documentReference2 = documentReference10;
                firebaseUser4 = firebaseUser9;
                i3 = i5;
                str16 = "PENDING";
                str8 = str35;
                documentReference5 = documentReference9;
                str17 = "deviceId";
                str14 = str32;
                str3 = str33;
                if (z4) {
                }
                return new AccessDecision(true, z4, str15, "تم اعتماد الجهاز");
            case 7:
                int i6 = c38431.I$0;
                boolean z8 = c38431.Z$0;
                str15 = (String) c38431.L$5;
                ResultKt.throwOnFailure(obj8);
                return new AccessDecision(false, false, str15, "تم إرسال طلب اعتماد هذا الجهاز إلى المدير");
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object pendingDeviceRequests(Continuation<? super List<DeviceRequest>> continuation) {
        C38471 c38471;
        if (continuation instanceof C38471) {
            c38471 = (C38471) continuation;
            if ((c38471.label & Integer.MIN_VALUE) != 0) {
                c38471.label -= Integer.MIN_VALUE;
            } else {
                c38471 = new C38471(continuation);
            }
        } else {
            c38471 = new C38471(continuation);
        }
        Object objAwait = c38471.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c38471.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAwait);
            Task<QuerySnapshot> task = firestore().collection("accessRequests").whereEqualTo(NotificationCompat.CATEGORY_STATUS, "PENDING").get();
            Intrinsics.checkNotNullExpressionValue(task, "get(...)");
            c38471.label = 1;
            objAwait = TasksKt.await(task, c38471);
            if (objAwait == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objAwait);
        }
        List<DocumentSnapshot> documents = ((QuerySnapshot) objAwait).getDocuments();
        Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
        List<DocumentSnapshot> list = documents;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (DocumentSnapshot documentSnapshot : list) {
            String id = documentSnapshot.getId();
            Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
            String string = documentSnapshot.getString("userId");
            if (string == null) {
                string = "";
            }
            String string2 = documentSnapshot.getString("email");
            if (string2 == null) {
                string2 = "";
            }
            String string3 = documentSnapshot.getString("deviceId");
            String str = string3 != null ? string3 : "";
            String string4 = documentSnapshot.getString("deviceName");
            if (string4 == null) {
                string4 = "جهاز Android";
            }
            Long l = documentSnapshot.getLong("requestedAt");
            arrayList.add(new DeviceRequest(id, string, string2, str, string4, l != null ? l.longValue() : 0L));
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x01c2 A[PHI: r4 r7
      0x01c2: PHI (r4v4 java.lang.String) = (r4v3 java.lang.String), (r4v10 java.lang.String) binds: [B:32:0x01bf, B:17:0x0058] A[DONT_GENERATE, DONT_INLINE]
      0x01c2: PHI (r7v4 com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$DeviceRequest) = 
      (r7v3 com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$DeviceRequest)
      (r7v8 com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync$DeviceRequest)
     binds: [B:32:0x01bf, B:17:0x0058] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x022a, code lost:
    
        if (kotlinx.coroutines.tasks.TasksKt.await(r0, r1) == r3) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object approveDevice(DeviceRequest deviceRequest, Continuation<? super Unit> continuation) {
        C38421 c38421;
        FirebaseCloudSync firebaseCloudSync;
        String uid;
        int i;
        int i2;
        DeviceRequest deviceRequest2;
        DeviceRequest deviceRequest3;
        Task<DocumentReference> taskAdd;
        if (continuation instanceof C38421) {
            c38421 = (C38421) continuation;
            if ((c38421.label & Integer.MIN_VALUE) != 0) {
                c38421.label -= Integer.MIN_VALUE;
                firebaseCloudSync = this;
            } else {
                firebaseCloudSync = this;
                c38421 = firebaseCloudSync.new C38421(continuation);
            }
        } else {
            firebaseCloudSync = this;
            c38421 = firebaseCloudSync.new C38421(continuation);
        }
        Object obj = c38421.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c38421.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser == null || (uid = currentUser.getUid()) == null) {
                throw new IllegalStateException("يجب أن تكون جلسة المدير فعالة".toString());
            }
            i = 2;
            i2 = 3;
            Task<Void> task = firebaseCloudSync.firestore().collection("devices").document(deviceRequest.getUserId() + "_" + deviceRequest.getDeviceId()).set(MapsKt.mapOf(TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, "APPROVED"), TuplesKt.m921to("approved", Boxing.boxBoolean(true)), TuplesKt.m921to("approvedBy", uid), TuplesKt.m921to("approvedAt", Boxing.boxLong(System.currentTimeMillis()))), SetOptions.merge());
            Intrinsics.checkNotNullExpressionValue(task, "set(...)");
            deviceRequest2 = deviceRequest;
            c38421.L$0 = deviceRequest2;
            c38421.L$1 = uid;
            c38421.label = 1;
            if (TasksKt.await(task, c38421) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i3 == 1) {
            uid = (String) c38421.L$1;
            deviceRequest2 = (DeviceRequest) c38421.L$0;
            ResultKt.throwOnFailure(obj);
            i2 = 3;
            i = 2;
        } else {
            if (i3 == 2) {
                uid = (String) c38421.L$1;
                deviceRequest3 = (DeviceRequest) c38421.L$0;
                ResultKt.throwOnFailure(obj);
                taskAdd = firebaseCloudSync.firestore().collection("notifications").add(MapsKt.mapOf(TuplesKt.m921to("userId", deviceRequest3.getUserId()), TuplesKt.m921to(LinkHeader.Parameters.Title, "اعتماد الجهاز"), TuplesKt.m921to("body", "تم اعتماد جهازك من المدير"), TuplesKt.m921to("read", Boxing.boxBoolean(false)), TuplesKt.m921to("createdAt", Boxing.boxLong(System.currentTimeMillis()))));
                Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
                c38421.L$0 = deviceRequest3;
                c38421.L$1 = uid;
                c38421.label = 3;
                if (TasksKt.await(taskAdd, c38421) != coroutine_suspended) {
                    Task<DocumentReference> taskAdd2 = firebaseCloudSync.firestore().collection("auditLogs").add(MapsKt.mapOf(TuplesKt.m921to("actorUid", uid), TuplesKt.m921to("action", "APPROVE_DEVICE"), TuplesKt.m921to("targetUserId", deviceRequest3.getUserId()), TuplesKt.m921to("targetDeviceId", deviceRequest3.getDeviceId()), TuplesKt.m921to("timestamp", Boxing.boxLong(System.currentTimeMillis()))));
                    Intrinsics.checkNotNullExpressionValue(taskAdd2, "add(...)");
                    c38421.L$0 = SpillingKt.nullOutSpilledVariable(deviceRequest3);
                    c38421.L$1 = SpillingKt.nullOutSpilledVariable(uid);
                    c38421.label = 4;
                }
                return coroutine_suspended;
            }
            if (i3 == 3) {
                uid = (String) c38421.L$1;
                deviceRequest3 = (DeviceRequest) c38421.L$0;
                ResultKt.throwOnFailure(obj);
                Task<DocumentReference> taskAdd3 = firebaseCloudSync.firestore().collection("auditLogs").add(MapsKt.mapOf(TuplesKt.m921to("actorUid", uid), TuplesKt.m921to("action", "APPROVE_DEVICE"), TuplesKt.m921to("targetUserId", deviceRequest3.getUserId()), TuplesKt.m921to("targetDeviceId", deviceRequest3.getDeviceId()), TuplesKt.m921to("timestamp", Boxing.boxLong(System.currentTimeMillis()))));
                Intrinsics.checkNotNullExpressionValue(taskAdd3, "add(...)");
                c38421.L$0 = SpillingKt.nullOutSpilledVariable(deviceRequest3);
                c38421.L$1 = SpillingKt.nullOutSpilledVariable(uid);
                c38421.label = 4;
            } else {
                if (i3 != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
        }
        return Unit.INSTANCE;
        DocumentReference documentReferenceDocument = firebaseCloudSync.firestore().collection("accessRequests").document(deviceRequest2.getId());
        Pair[] pairArr = new Pair[i2];
        pairArr[0] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, "APPROVED");
        pairArr[1] = TuplesKt.m921to("approvedBy", uid);
        pairArr[i] = TuplesKt.m921to("approvedAt", Boxing.boxLong(System.currentTimeMillis()));
        Task<Void> task2 = documentReferenceDocument.set(MapsKt.mapOf(pairArr), SetOptions.merge());
        Intrinsics.checkNotNullExpressionValue(task2, "set(...)");
        c38421.L$0 = deviceRequest2;
        c38421.L$1 = uid;
        c38421.label = i;
        if (TasksKt.await(task2, c38421) != coroutine_suspended) {
            deviceRequest3 = deviceRequest2;
            taskAdd = firebaseCloudSync.firestore().collection("notifications").add(MapsKt.mapOf(TuplesKt.m921to("userId", deviceRequest3.getUserId()), TuplesKt.m921to(LinkHeader.Parameters.Title, "اعتماد الجهاز"), TuplesKt.m921to("body", "تم اعتماد جهازك من المدير"), TuplesKt.m921to("read", Boxing.boxBoolean(false)), TuplesKt.m921to("createdAt", Boxing.boxLong(System.currentTimeMillis()))));
            Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
            c38421.L$0 = deviceRequest3;
            c38421.L$1 = uid;
            c38421.label = 3;
            if (TasksKt.await(taskAdd, c38421) != coroutine_suspended) {
                Task<DocumentReference> taskAdd4 = firebaseCloudSync.firestore().collection("auditLogs").add(MapsKt.mapOf(TuplesKt.m921to("actorUid", uid), TuplesKt.m921to("action", "APPROVE_DEVICE"), TuplesKt.m921to("targetUserId", deviceRequest3.getUserId()), TuplesKt.m921to("targetDeviceId", deviceRequest3.getDeviceId()), TuplesKt.m921to("timestamp", Boxing.boxLong(System.currentTimeMillis()))));
                Intrinsics.checkNotNullExpressionValue(taskAdd4, "add(...)");
                c38421.L$0 = SpillingKt.nullOutSpilledVariable(deviceRequest3);
                c38421.L$1 = SpillingKt.nullOutSpilledVariable(uid);
                c38421.label = 4;
            }
        }
        return coroutine_suspended;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00cf, code lost:
    
        if (r7.replaceRemoteDocuments(r6, r0) == r1) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object syncDocuments(MasrofRepository masrofRepository, Continuation<? super Integer> continuation) {
        C38541 c38541;
        ArrayList arrayList;
        if (continuation instanceof C38541) {
            c38541 = (C38541) continuation;
            if ((c38541.label & Integer.MIN_VALUE) != 0) {
                c38541.label -= Integer.MIN_VALUE;
            } else {
                c38541 = new C38541(continuation);
            }
        } else {
            c38541 = new C38541(continuation);
        }
        Object objAwait = c38541.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c38541.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAwait);
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                return Boxing.boxInt(0);
            }
            Task<QuerySnapshot> task = firestore().collection("documents").get();
            Intrinsics.checkNotNullExpressionValue(task, "get(...)");
            c38541.L$0 = masrofRepository;
            c38541.label = 1;
            objAwait = TasksKt.await(task, c38541);
            if (objAwait != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            masrofRepository = (MasrofRepository) c38541.L$0;
            ResultKt.throwOnFailure(objAwait);
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            arrayList = (List) c38541.L$2;
            ResultKt.throwOnFailure(objAwait);
        }
        return Boxing.boxInt(arrayList.size());
        QuerySnapshot querySnapshot = (QuerySnapshot) objAwait;
        List<DocumentSnapshot> documents = querySnapshot.getDocuments();
        Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
        ArrayList arrayList2 = new ArrayList();
        for (DocumentSnapshot documentSnapshot : documents) {
            FirebaseCloudSync firebaseCloudSync = INSTANCE;
            Intrinsics.checkNotNull(documentSnapshot);
            Document localDocument = firebaseCloudSync.toLocalDocument(documentSnapshot);
            if (localDocument != null) {
                arrayList2.add(localDocument);
            }
        }
        arrayList = arrayList2;
        if (!arrayList.isEmpty()) {
            c38541.L$0 = SpillingKt.nullOutSpilledVariable(masrofRepository);
            c38541.L$1 = SpillingKt.nullOutSpilledVariable(querySnapshot);
            c38541.L$2 = arrayList;
            c38541.label = 2;
        }
        return Boxing.boxInt(arrayList.size());
    }

    public final Object saveDocument(Document document, Continuation<? super Unit> continuation) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            return Unit.INSTANCE;
        }
        String cloudId = document.getCloudId();
        if (StringsKt.isBlank(cloudId)) {
            cloudId = "android_" + document.getId();
        }
        String str = cloudId;
        DocumentReference documentReferenceDocument = firestore().collection("documents").document(str);
        Pair[] pairArr = new Pair[25];
        pairArr[0] = TuplesKt.m921to("id", str);
        pairArr[1] = TuplesKt.m921to(LinkHeader.Parameters.Type, document.getType().name());
        pairArr[2] = TuplesKt.m921to("documentNumber", document.getDocumentNumber());
        pairArr[3] = TuplesKt.m921to("dateHijri", document.getDateHijri());
        pairArr[4] = TuplesKt.m921to("dateGregorian", document.getDateGregorian());
        pairArr[5] = TuplesKt.m921to("amount", document.getAmount());
        pairArr[6] = TuplesKt.m921to("amountWords", document.getAmountWords());
        pairArr[7] = TuplesKt.m921to("beneficiaryName", document.getBeneficiaryName());
        pairArr[8] = TuplesKt.m921to("purpose", document.getPurpose());
        pairArr[9] = TuplesKt.m921to("details", document.getDetails());
        pairArr[10] = TuplesKt.m921to("notes", document.getNotes());
        pairArr[11] = TuplesKt.m921to(NotificationCompat.CATEGORY_STATUS, document.getStatus().name());
        pairArr[12] = TuplesKt.m921to("attachmentsCount", Boxing.boxInt(document.getAttachmentsCount()));
        pairArr[13] = TuplesKt.m921to("createdAt", Boxing.boxLong(document.getCreatedAt()));
        pairArr[14] = TuplesKt.m921to("isArchived", Boxing.boxBoolean(document.isArchived()));
        pairArr[15] = TuplesKt.m921to("archivedAt", document.getArchivedAt());
        pairArr[16] = TuplesKt.m921to("updatedAt", Boxing.boxLong(System.currentTimeMillis()));
        List listSplit$default = StringsKt.split$default((CharSequence) document.getTags(), new char[]{AbstractJsonLexerKt.COMMA}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listSplit$default) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        pairArr[17] = TuplesKt.m921to("tags", arrayList);
        pairArr[18] = TuplesKt.m921to("expenseItem", document.getFinancialCategory());
        pairArr[19] = TuplesKt.m921to("costCenter", document.getCostCenter());
        pairArr[20] = TuplesKt.m921to("fundingSource", document.getFundingSource());
        pairArr[21] = TuplesKt.m921to("beneficiaryId", document.getBeneficiaryId());
        pairArr[22] = TuplesKt.m921to("requesterName", document.getSubmittedBy());
        pairArr[23] = TuplesKt.m921to("managerName", document.getApprovedBy());
        pairArr[24] = TuplesKt.m921to("createdByUid", currentUser.getUid());
        Task<Void> task = documentReferenceDocument.set(MapsKt.mapOf(pairArr), SetOptions.merge());
        Intrinsics.checkNotNullExpressionValue(task, "set(...)");
        Object objAwait = TasksKt.await(task, continuation);
        return objAwait == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwait : Unit.INSTANCE;
    }

    private final Document toLocalDocument(DocumentSnapshot documentSnapshot) {
        Object objM7781constructorimpl;
        Object objM7781constructorimpl2;
        String string;
        List listFilterNotNull;
        try {
            Result.Companion companion = Result.INSTANCE;
            String string2 = documentSnapshot.getString(LinkHeader.Parameters.Type);
            if (string2 == null) {
                string2 = "ORDER";
            }
            objM7781constructorimpl = Result.m7781constructorimpl(DocumentType.valueOf(string2));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        DocumentType documentType = DocumentType.ORDER;
        if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
            objM7781constructorimpl = documentType;
        }
        DocumentType documentType2 = (DocumentType) objM7781constructorimpl;
        try {
            Result.Companion companion3 = Result.INSTANCE;
            String string3 = documentSnapshot.getString(NotificationCompat.CATEGORY_STATUS);
            if (string3 == null) {
                string3 = "SUBMITTED";
            }
            objM7781constructorimpl2 = Result.m7781constructorimpl(DocumentStatus.valueOf(string3));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM7781constructorimpl2 = Result.m7781constructorimpl(ResultKt.createFailure(th2));
        }
        DocumentStatus documentStatus = DocumentStatus.SUBMITTED;
        if (Result.m7787isFailureimpl(objM7781constructorimpl2)) {
            objM7781constructorimpl2 = documentStatus;
        }
        DocumentStatus documentStatus2 = (DocumentStatus) objM7781constructorimpl2;
        Long l = documentSnapshot.getLong("createdAt");
        long jLongValue = l != null ? l.longValue() : System.currentTimeMillis();
        long jHashCode = documentSnapshot.getId().hashCode();
        long jAbs = jHashCode == 0 ? 1L : Math.abs(jHashCode);
        Object obj = documentSnapshot.get("tags");
        List list = obj instanceof List ? (List) obj : null;
        String str = ((list == null || (listFilterNotNull = CollectionsKt.filterNotNull(list)) == null || (string = CollectionsKt.joinToString$default(listFilterNotNull, ",", null, null, 0, null, null, 62, null)) == null) && (string = documentSnapshot.getString("tags")) == null) ? "" : string;
        String string4 = documentSnapshot.getString("documentNumber");
        if (string4 == null && (string4 = documentSnapshot.getString("serialNumber")) == null) {
            string4 = documentSnapshot.getId();
            Intrinsics.checkNotNullExpressionValue(string4, "getId(...)");
        }
        String string5 = documentSnapshot.getString("dateHijri");
        if (string5 == null) {
            string5 = "";
        }
        String string6 = documentSnapshot.getString("dateGregorian");
        if (string6 == null && (string6 = documentSnapshot.getString("dateString")) == null) {
            string6 = "";
        }
        Double d = documentSnapshot.getDouble("amount");
        String string7 = documentSnapshot.getString("amountWords");
        String string8 = documentSnapshot.getString("beneficiaryName");
        if (string8 == null) {
            string8 = documentSnapshot.getString("beneficiary");
        }
        String string9 = documentSnapshot.getString("purpose");
        if (string9 == null) {
            string9 = documentSnapshot.getString(LinkHeader.Parameters.Title);
        }
        String string10 = documentSnapshot.getString("details");
        String string11 = documentSnapshot.getString("notes");
        Long l2 = documentSnapshot.getLong("attachmentsCount");
        int iLongValue = (int) (l2 != null ? l2.longValue() : 0L);
        Boolean bool = documentSnapshot.getBoolean("isArchived");
        boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
        Long l3 = documentSnapshot.getLong("archivedAt");
        Long l4 = documentSnapshot.getLong("updatedAt");
        long jLongValue2 = l4 != null ? l4.longValue() : jLongValue;
        String string12 = documentSnapshot.getString("expenseItem");
        String str2 = string12 == null ? "" : string12;
        String string13 = documentSnapshot.getString("costCenter");
        String str3 = string13 == null ? "" : string13;
        String string14 = documentSnapshot.getString("fundingSource");
        String str4 = string14 == null ? "" : string14;
        String string15 = documentSnapshot.getString("beneficiaryId");
        String str5 = string15 == null ? "" : string15;
        String string16 = documentSnapshot.getString("requesterName");
        String str6 = string16 == null ? "" : string16;
        String string17 = documentSnapshot.getString("managerName");
        String str7 = string17 != null ? string17 : "";
        String id = documentSnapshot.getId();
        Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
        return new Document(jAbs, documentType2, string4, string5, string6, d, string7, string8, string9, string10, string11, documentStatus2, iLongValue, jLongValue, zBooleanValue, l3, jLongValue2, str, str2, str3, str4, str5, str6, "", str7, null, null, "", id);
    }
}
