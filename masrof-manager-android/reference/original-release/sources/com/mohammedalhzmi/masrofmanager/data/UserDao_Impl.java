package com.mohammedalhzmi.masrofmanager.data;

import androidx.autofill.HintConstants;
import androidx.room.EntityDeleteOrUpdateAdapter;
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

/* JADX INFO: compiled from: UserDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00150\u0014H\u0016J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@¢\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/UserDao_Impl;", "Lcom/mohammedalhzmi/masrofmanager/data/UserDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfUserEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/mohammedalhzmi/masrofmanager/data/UserEntity;", "__deleteAdapterOfUserEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__updateAdapterOfUserEntity", "insert", "", "user", "(Lcom/mohammedalhzmi/masrofmanager/data/UserEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "update", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "", "findActive", HintConstants.AUTOFILL_HINT_USERNAME, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "count", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class UserDao_Impl implements UserDao {
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<UserEntity> __deleteAdapterOfUserEntity;
    private final EntityInsertAdapter<UserEntity> __insertAdapterOfUserEntity;
    private final EntityDeleteOrUpdateAdapter<UserEntity> __updateAdapterOfUserEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public UserDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfUserEntity = new EntityInsertAdapter<UserEntity>() { // from class: com.mohammedalhzmi.masrofmanager.data.UserDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR ABORT INTO `users` (`id`,`username`,`passwordHash`,`fullName`,`role`,`active`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, UserEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
                statement.mo7549bindText(2, entity.getUsername());
                statement.mo7549bindText(3, entity.getPasswordHash());
                statement.mo7549bindText(4, entity.getFullName());
                statement.mo7549bindText(5, entity.getRole());
                statement.mo7547bindLong(6, entity.getActive() ? 1L : 0L);
                statement.mo7547bindLong(7, entity.getCreatedAt());
            }
        };
        this.__deleteAdapterOfUserEntity = new EntityDeleteOrUpdateAdapter<UserEntity>() { // from class: com.mohammedalhzmi.masrofmanager.data.UserDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `users` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, UserEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
            }
        };
        this.__updateAdapterOfUserEntity = new EntityDeleteOrUpdateAdapter<UserEntity>() { // from class: com.mohammedalhzmi.masrofmanager.data.UserDao_Impl.3
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `users` SET `id` = ?,`username` = ?,`passwordHash` = ?,`fullName` = ?,`role` = ?,`active` = ?,`createdAt` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, UserEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
                statement.mo7549bindText(2, entity.getUsername());
                statement.mo7549bindText(3, entity.getPasswordHash());
                statement.mo7549bindText(4, entity.getFullName());
                statement.mo7549bindText(5, entity.getRole());
                statement.mo7547bindLong(6, entity.getActive() ? 1L : 0L);
                statement.mo7547bindLong(7, entity.getCreatedAt());
                statement.mo7547bindLong(8, entity.getId());
            }
        };
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.UserDao
    public Object insert(final UserEntity userEntity, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.UserDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Long.valueOf(UserDao_Impl.insert$lambda$0(this.f$0, userEntity, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    static final long insert$lambda$0(UserDao_Impl userDao_Impl, UserEntity userEntity, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        return userDao_Impl.__insertAdapterOfUserEntity.insertAndReturnId(_connection, userEntity);
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.UserDao
    public Object delete(final UserEntity userEntity, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.UserDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserDao_Impl.delete$lambda$1(this.f$0, userEntity, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit delete$lambda$1(UserDao_Impl userDao_Impl, UserEntity userEntity, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        userDao_Impl.__deleteAdapterOfUserEntity.handle(_connection, userEntity);
        return Unit.INSTANCE;
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.UserDao
    public Object update(final UserEntity userEntity, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.UserDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserDao_Impl.update$lambda$2(this.f$0, userEntity, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit update$lambda$2(UserDao_Impl userDao_Impl, UserEntity userEntity, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        userDao_Impl.__updateAdapterOfUserEntity.handle(_connection, userEntity);
        return Unit.INSTANCE;
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.UserDao
    public Flow<List<UserEntity>> observeAll() {
        final String str = "SELECT * FROM users ORDER BY fullName";
        return FlowUtil.createFlow(this.__db, false, new String[]{"users"}, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.UserDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserDao_Impl.observeAll$lambda$3(str, (SQLiteConnection) obj);
            }
        });
    }

    static final List observeAll$lambda$3(String str, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, HintConstants.AUTOFILL_HINT_USERNAME);
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "passwordHash");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fullName");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "role");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "active");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "createdAt");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                arrayList.add(new UserEntity(sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.getText(columnIndexOrThrow2), sQLiteStatementPrepare.getText(columnIndexOrThrow3), sQLiteStatementPrepare.getText(columnIndexOrThrow4), sQLiteStatementPrepare.getText(columnIndexOrThrow5), ((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6)) != 0, sQLiteStatementPrepare.getLong(columnIndexOrThrow7)));
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th) {
            sQLiteStatementPrepare.close();
            throw th;
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.UserDao
    public Object findActive(final String str, Continuation<? super UserEntity> continuation) {
        final String str2 = "SELECT * FROM users WHERE username = ? AND active = 1 LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.UserDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserDao_Impl.findActive$lambda$4(str2, str, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final UserEntity findActive$lambda$4(String str, String str2, SQLiteConnection _connection) {
        UserEntity userEntity;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        boolean z = true;
        try {
            sQLiteStatementPrepare.mo7549bindText(1, str2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, HintConstants.AUTOFILL_HINT_USERNAME);
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "passwordHash");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fullName");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "role");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "active");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "createdAt");
            if (sQLiteStatementPrepare.step()) {
                long j = sQLiteStatementPrepare.getLong(columnIndexOrThrow);
                String text = sQLiteStatementPrepare.getText(columnIndexOrThrow2);
                String text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow3);
                String text3 = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                String text4 = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                if (((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow6)) == 0) {
                    z = false;
                }
                userEntity = new UserEntity(j, text, text2, text3, text4, z, sQLiteStatementPrepare.getLong(columnIndexOrThrow7));
            } else {
                userEntity = null;
            }
            return userEntity;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.UserDao
    public Object count(Continuation<? super Integer> continuation) {
        final String str = "SELECT COUNT(*) FROM users";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.UserDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(UserDao_Impl.count$lambda$5(str, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    static final int count$lambda$5(String str, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            return sQLiteStatementPrepare.step() ? (int) sQLiteStatementPrepare.getLong(0) : 0;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    /* JADX INFO: compiled from: UserDao_Impl.kt */
    @Metadata(m913d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/UserDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
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
