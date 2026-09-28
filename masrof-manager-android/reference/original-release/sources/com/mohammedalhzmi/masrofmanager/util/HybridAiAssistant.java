package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import androidx.browser.customtabs.CustomTabsCallback;
import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity;
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HybridAiAssistant.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JF\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0002\u0010\u0011¨\u0006\u0013"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/HybridAiAssistant;", "", "<init>", "()V", "plan", "Lcom/mohammedalhzmi/masrofmanager/util/HybridAiAssistant$Result;", "context", "Landroid/content/Context;", "apiKey", "", "endpoint", "instruction", "elements", "", "Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "design", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Result", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class HybridAiAssistant {
    public static final int $stable = 0;
    public static final HybridAiAssistant INSTANCE = new HybridAiAssistant();

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.util.HybridAiAssistant$plan$1 */
    /* JADX INFO: compiled from: HybridAiAssistant.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.util.HybridAiAssistant", m938f = "HybridAiAssistant.kt", m939i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, m940l = {14}, m941m = "plan", m942n = {"context", "apiKey", "endpoint", "instruction", "elements", "design", "$this$plan_u24lambda_u241", CustomTabsCallback.ONLINE_EXTRAS_KEY, "$i$a$-runCatching-HybridAiAssistant$plan$2"}, m943s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1"})
    static final class C41001 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C41001(Continuation<? super C41001> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HybridAiAssistant.this.plan(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: compiled from: HybridAiAssistant.kt */
    @Metadata(m913d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/HybridAiAssistant$Result;", "", "commands", "", "Lcom/mohammedalhzmi/masrofmanager/util/AiLayoutAssistant$Command;", "mode", "", "<init>", "(Ljava/util/List;Ljava/lang/String;)V", "getCommands", "()Ljava/util/List;", "getMode", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* data */ class Result {
        public static final int $stable = 8;
        private final List<AiLayoutAssistant.Command> commands;
        private final String mode;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Result copy$default(Result result, List list, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                list = result.commands;
            }
            if ((i & 2) != 0) {
                str = result.mode;
            }
            return result.copy(list, str);
        }

        public final List<AiLayoutAssistant.Command> component1() {
            return this.commands;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMode() {
            return this.mode;
        }

        public final Result copy(List<AiLayoutAssistant.Command> commands, String mode) {
            Intrinsics.checkNotNullParameter(commands, "commands");
            Intrinsics.checkNotNullParameter(mode, "mode");
            return new Result(commands, mode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return Intrinsics.areEqual(this.commands, result.commands) && Intrinsics.areEqual(this.mode, result.mode);
        }

        public int hashCode() {
            return (this.commands.hashCode() * 31) + this.mode.hashCode();
        }

        public String toString() {
            return "Result(commands=" + this.commands + ", mode=" + this.mode + ")";
        }

        public Result(List<AiLayoutAssistant.Command> commands, String mode) {
            Intrinsics.checkNotNullParameter(commands, "commands");
            Intrinsics.checkNotNullParameter(mode, "mode");
            this.commands = commands;
            this.mode = mode;
        }

        public final List<AiLayoutAssistant.Command> getCommands() {
            return this.commands;
        }

        public final String getMode() {
            return this.mode;
        }
    }

    private HybridAiAssistant() {
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v19 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6, types: [int] */
    public final Object plan(Context context, String str, String str2, String str3, List<DesignElementEntity> list, DocumentDesignEntity documentDesignEntity, Continuation<? super Result> continuation) {
        C41001 c41001;
        ?? AreEqual;
        String str4;
        List<DesignElementEntity> list2;
        Throwable th;
        List<DesignElementEntity> list3;
        String str5;
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        Object objM7781constructorimpl;
        if (continuation instanceof C41001) {
            c41001 = (C41001) continuation;
            if ((c41001.label & Integer.MIN_VALUE) != 0) {
                c41001.label -= Integer.MIN_VALUE;
            } else {
                c41001 = new C41001(continuation);
            }
        } else {
            c41001 = new C41001(continuation);
        }
        Object objPlan = c41001.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c41001.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objPlan);
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
            if (connectivityManager == null || (activeNetwork = connectivityManager.getActiveNetwork()) == null) {
                AreEqual = 0;
            } else {
                ConnectivityManager connectivityManager2 = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
                AreEqual = Intrinsics.areEqual((connectivityManager2 == null || (networkCapabilities = connectivityManager2.getNetworkCapabilities(activeNetwork)) == null) ? null : Boxing.boxBoolean(networkCapabilities.hasCapability(12)), Boxing.boxBoolean(true));
            }
            if (AreEqual == 0 || StringsKt.isBlank(str)) {
                return new Result(LocalLayoutRules.INSTANCE.plan(str3, list, documentDesignEntity), "محلي دون اتصال");
            }
            try {
                kotlin.Result.Companion companion = kotlin.Result.INSTANCE;
                HybridAiAssistant hybridAiAssistant = this;
                AiLayoutAssistant aiLayoutAssistant = AiLayoutAssistant.INSTANCE;
                c41001.L$0 = SpillingKt.nullOutSpilledVariable(context);
                c41001.L$1 = SpillingKt.nullOutSpilledVariable(str);
                c41001.L$2 = SpillingKt.nullOutSpilledVariable(str2);
                c41001.L$3 = str3;
                c41001.L$4 = list;
                c41001.L$5 = documentDesignEntity;
                c41001.L$6 = SpillingKt.nullOutSpilledVariable(this);
                c41001.I$0 = AreEqual;
                c41001.I$1 = 0;
                c41001.label = 1;
                str4 = str3;
                list2 = list;
                try {
                    objPlan = aiLayoutAssistant.plan(str, str2, str4, list2, documentDesignEntity, c41001);
                    if (objPlan == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    documentDesignEntity = documentDesignEntity;
                    list3 = list2;
                    str5 = str4;
                } catch (Throwable th2) {
                    th = th2;
                    documentDesignEntity = documentDesignEntity;
                    list3 = list2;
                    str5 = str4;
                    kotlin.Result.Companion companion2 = kotlin.Result.INSTANCE;
                    objM7781constructorimpl = kotlin.Result.m7781constructorimpl(ResultKt.createFailure(th));
                }
            } catch (Throwable th3) {
                str4 = str3;
                list2 = list;
                th = th3;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c41001.I$1;
            int i3 = c41001.I$0;
            documentDesignEntity = (DocumentDesignEntity) c41001.L$5;
            list3 = (List) c41001.L$4;
            str5 = (String) c41001.L$3;
            try {
                ResultKt.throwOnFailure(objPlan);
            } catch (Throwable th4) {
                th = th4;
                kotlin.Result.Companion companion3 = kotlin.Result.INSTANCE;
                objM7781constructorimpl = kotlin.Result.m7781constructorimpl(ResultKt.createFailure(th));
            }
        }
        objM7781constructorimpl = kotlin.Result.m7781constructorimpl(new Result((List) objPlan, "سحابي"));
        return kotlin.Result.m7784exceptionOrNullimpl(objM7781constructorimpl) == null ? objM7781constructorimpl : new Result(LocalLayoutRules.INSTANCE.plan(str5, list3, documentDesignEntity), "محلي احتياطي");
    }
}
