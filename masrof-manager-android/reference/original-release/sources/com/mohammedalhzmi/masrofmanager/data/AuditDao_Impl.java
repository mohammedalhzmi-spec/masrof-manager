package com.mohammedalhzmi.masrofmanager.data;

import androidx.autofill.HintConstants;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: AuditDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f0\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/AuditDao_Impl;", "Lcom/mohammedalhzmi/masrofmanager/data/AuditDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfAuditLogEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/mohammedalhzmi/masrofmanager/data/AuditLogEntity;", "insert", "", "log", "(Lcom/mohammedalhzmi/masrofmanager/data/AuditLogEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "", "Companion", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class AuditDao_Impl implements AuditDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<AuditLogEntity> __insertAdapterOfAuditLogEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public AuditDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfAuditLogEntity = new EntityInsertAdapter<AuditLogEntity>() { // from class: com.mohammedalhzmi.masrofmanager.data.AuditDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR ABORT INTO `audit_logs` (`id`,`userId`,`username`,`action`,`details`,`timestamp`) VALUES (nullif(?, 0),?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, AuditLogEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
                Long userId = entity.getUserId();
                if (userId == null) {
                    statement.mo7548bindNull(2);
                } else {
                    statement.mo7547bindLong(2, userId.longValue());
                }
                statement.mo7549bindText(3, entity.getUsername());
                statement.mo7549bindText(4, entity.getAction());
                statement.mo7549bindText(5, entity.getDetails());
                statement.mo7547bindLong(6, entity.getTimestamp());
            }
        };
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.AuditDao
    public Object insert(final AuditLogEntity auditLogEntity, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.AuditDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AuditDao_Impl.insert$lambda$0(this.f$0, auditLogEntity, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insert$lambda$0(AuditDao_Impl auditDao_Impl, AuditLogEntity auditLogEntity, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        auditDao_Impl.__insertAdapterOfAuditLogEntity.insert(_connection, auditLogEntity);
        return Unit.INSTANCE;
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.AuditDao
    public Flow<List<AuditLogEntity>> observeAll() {
        final String str = "SELECT * FROM audit_logs ORDER BY timestamp DESC LIMIT 500";
        return FlowUtil.createFlow(this.__db, false, new String[]{"audit_logs"}, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.AuditDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AuditDao_Impl.observeAll$lambda$1(str, (SQLiteConnection) obj);
            }
        });
    }

    static final List observeAll$lambda$1(String str, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "userId");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, HintConstants.AUTOFILL_HINT_USERNAME);
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "action");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "details");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "timestamp");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                arrayList.add(new AuditLogEntity(sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.isNull(columnIndexOrThrow2) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(columnIndexOrThrow2)), sQLiteStatementPrepare.getText(columnIndexOrThrow3), sQLiteStatementPrepare.getText(columnIndexOrThrow4), sQLiteStatementPrepare.getText(columnIndexOrThrow5), sQLiteStatementPrepare.getLong(columnIndexOrThrow6)));
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    /* JADX INFO: compiled from: AuditDao_Impl.kt */
    @Metadata(m913d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/AuditDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }
}
