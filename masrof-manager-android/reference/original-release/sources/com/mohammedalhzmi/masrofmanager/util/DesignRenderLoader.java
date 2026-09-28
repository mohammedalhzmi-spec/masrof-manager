package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import androidx.room.Room;
import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity;
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import com.mohammedalhzmi.masrofmanager.data.MasrofDatabase;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DesignRenderLoader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\r"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/DesignRenderLoader;", "", "<init>", "()V", "design", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;", "context", "Landroid/content/Context;", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "elements", "", "Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class DesignRenderLoader {
    public static final int $stable = 0;
    public static final DesignRenderLoader INSTANCE = new DesignRenderLoader();

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.util.DesignRenderLoader$design$1 */
    /* JADX INFO: compiled from: DesignRenderLoader.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.util.DesignRenderLoader$design$1", m938f = "DesignRenderLoader.kt", m939i = {0}, m940l = {15}, m941m = "invokeSuspend", m942n = {"db"}, m943s = {"L$0"})
    static final class C40981 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DocumentDesignEntity>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ DocumentType $type;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40981(Context context, DocumentType documentType, Continuation<? super C40981> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$type = documentType;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C40981(this.$context, this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super DocumentDesignEntity> continuation) {
            return ((C40981) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Throwable th;
            MasrofDatabase masrofDatabase;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Context applicationContext = this.$context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                MasrofDatabase masrofDatabase2 = (MasrofDatabase) Room.databaseBuilder(applicationContext, MasrofDatabase.class, "masrof-db").addMigrations(MasrofDatabase.INSTANCE.getMIGRATION_3_4(), MasrofDatabase.INSTANCE.getMIGRATION_4_5(), MasrofDatabase.INSTANCE.getMIGRATION_5_6(), MasrofDatabase.INSTANCE.getMIGRATION_6_7(), MasrofDatabase.INSTANCE.getMIGRATION_7_8(), MasrofDatabase.INSTANCE.getMIGRATION_8_9(), MasrofDatabase.INSTANCE.getMIGRATION_9_10()).build();
                try {
                    this.L$0 = masrofDatabase2;
                    this.label = 1;
                    Object design = masrofDatabase2.designDao().getDesign(this.$type.name(), this);
                    if (design == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj = design;
                    masrofDatabase = masrofDatabase2;
                } catch (Throwable th2) {
                    th = th2;
                    masrofDatabase = masrofDatabase2;
                    masrofDatabase.close();
                    throw th;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                masrofDatabase = (MasrofDatabase) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Throwable th3) {
                    th = th3;
                    masrofDatabase.close();
                    throw th;
                }
            }
            DocumentDesignEntity documentDesignEntity = (DocumentDesignEntity) obj;
            masrofDatabase.close();
            return documentDesignEntity;
        }
    }

    private DesignRenderLoader() {
    }

    public final DocumentDesignEntity design(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        return (DocumentDesignEntity) BuildersKt__BuildersKt.runBlocking$default(null, new C40981(context, type, null), 1, null);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.util.DesignRenderLoader$elements$1 */
    /* JADX INFO: compiled from: DesignRenderLoader.kt */
    @Metadata(m913d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, m914d2 = {"<anonymous>", "", "Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.util.DesignRenderLoader$elements$1", m938f = "DesignRenderLoader.kt", m939i = {0, 1, 1}, m940l = {22, 23}, m941m = "invokeSuspend", m942n = {"db", "db", "design"}, m943s = {"L$0", "L$0", "L$1"})
    static final class C40991 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends DesignElementEntity>>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ DocumentType $type;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C40991(Context context, DocumentType documentType, Continuation<? super C40991> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$type = documentType;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C40991(this.$context, this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends DesignElementEntity>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<DesignElementEntity>>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<DesignElementEntity>> continuation) {
            return ((C40991) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:24:0x00b2 A[Catch: all -> 0x00df, TRY_LEAVE, TryCatch #2 {all -> 0x00df, blocks: (B:22:0x00ae, B:24:0x00b2, B:27:0x00ba, B:19:0x0096), top: B:42:0x0096 }] */
        /* JADX WARN: Code duplicated, block: B:27:0x00ba A[Catch: all -> 0x00df, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x00df, blocks: (B:22:0x00ae, B:24:0x00b2, B:27:0x00ba, B:19:0x0096), top: B:42:0x0096 }] */
        /* JADX WARN: Code duplicated, block: B:30:0x00d6  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            MasrofDatabase masrofDatabase;
            Throwable th;
            MasrofDatabase masrofDatabase2;
            Object design;
            DocumentDesignEntity documentDesignEntity;
            Object elements;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Context applicationContext = this.$context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                masrofDatabase = (MasrofDatabase) Room.databaseBuilder(applicationContext, MasrofDatabase.class, "masrof-db").addMigrations(MasrofDatabase.INSTANCE.getMIGRATION_3_4(), MasrofDatabase.INSTANCE.getMIGRATION_4_5(), MasrofDatabase.INSTANCE.getMIGRATION_5_6(), MasrofDatabase.INSTANCE.getMIGRATION_6_7(), MasrofDatabase.INSTANCE.getMIGRATION_7_8(), MasrofDatabase.INSTANCE.getMIGRATION_8_9(), MasrofDatabase.INSTANCE.getMIGRATION_9_10()).build();
                try {
                    this.L$0 = masrofDatabase;
                    this.label = 1;
                    design = masrofDatabase.designDao().getDesign(this.$type.name(), this);
                    if (design != coroutine_suspended) {
                        documentDesignEntity = (DocumentDesignEntity) design;
                        if (documentDesignEntity == null) {
                            this.L$0 = masrofDatabase;
                            this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity);
                            this.label = 2;
                            elements = masrofDatabase.designDao().getElements(documentDesignEntity.getId(), this);
                            if (elements != coroutine_suspended) {
                                MasrofDatabase masrofDatabase3 = masrofDatabase;
                                obj = elements;
                                masrofDatabase2 = masrofDatabase3;
                                List list = (List) obj;
                                masrofDatabase2.close();
                                return list;
                            }
                        } else {
                            List listEmptyList = CollectionsKt.emptyList();
                            masrofDatabase.close();
                            return listEmptyList;
                        }
                    }
                    return coroutine_suspended;
                } catch (Throwable th2) {
                    th = th2;
                    masrofDatabase2 = masrofDatabase;
                    masrofDatabase2.close();
                    throw th;
                }
            }
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                masrofDatabase2 = (MasrofDatabase) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    List list2 = (List) obj;
                    masrofDatabase2.close();
                    return list2;
                } catch (Throwable th3) {
                    th = th3;
                    masrofDatabase2.close();
                    throw th;
                }
            }
            MasrofDatabase masrofDatabase4 = (MasrofDatabase) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                design = obj;
                masrofDatabase = masrofDatabase4;
                documentDesignEntity = (DocumentDesignEntity) design;
                if (documentDesignEntity == null) {
                    this.L$0 = masrofDatabase;
                    this.L$1 = SpillingKt.nullOutSpilledVariable(documentDesignEntity);
                    this.label = 2;
                    elements = masrofDatabase.designDao().getElements(documentDesignEntity.getId(), this);
                    if (elements != coroutine_suspended) {
                        MasrofDatabase masrofDatabase5 = masrofDatabase;
                        obj = elements;
                        masrofDatabase2 = masrofDatabase5;
                        List list3 = (List) obj;
                        masrofDatabase2.close();
                        return list3;
                    }
                    return coroutine_suspended;
                }
                List listEmptyList2 = CollectionsKt.emptyList();
                masrofDatabase.close();
                return listEmptyList2;
            } catch (Throwable th4) {
                th = th4;
                masrofDatabase2 = masrofDatabase4;
                masrofDatabase2.close();
                throw th;
            }
        }
    }

    public final List<DesignElementEntity> elements(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        return (List) BuildersKt__BuildersKt.runBlocking$default(null, new C40991(context, type, null), 1, null);
    }
}
