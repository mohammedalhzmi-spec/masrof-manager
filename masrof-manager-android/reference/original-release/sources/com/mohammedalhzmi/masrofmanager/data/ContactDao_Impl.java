package com.mohammedalhzmi.masrofmanager.data;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import io.ktor.http.LinkHeader;
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

/* JADX INFO: compiled from: ContactDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00140\u0013H\u0016J\u001c\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00140\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/ContactDao_Impl;", "Lcom/mohammedalhzmi/masrofmanager/data/ContactDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfContactEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/mohammedalhzmi/masrofmanager/data/ContactEntity;", "__converters", "Lcom/mohammedalhzmi/masrofmanager/data/Converters;", "__deleteAdapterOfContactEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "insertContact", "", "contact", "(Lcom/mohammedalhzmi/masrofmanager/data/ContactEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteContact", "getAllContacts", "Lkotlinx/coroutines/flow/Flow;", "", "getContactsByType", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/ContactType;", "Companion", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class ContactDao_Impl implements ContactDao {
    private final Converters __converters;
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<ContactEntity> __deleteAdapterOfContactEntity;
    private final EntityInsertAdapter<ContactEntity> __insertAdapterOfContactEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public ContactDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__converters = new Converters();
        this.__db = __db;
        this.__insertAdapterOfContactEntity = new EntityInsertAdapter<ContactEntity>() { // from class: com.mohammedalhzmi.masrofmanager.data.ContactDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `contacts` (`id`,`name`,`type`) VALUES (nullif(?, 0),?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, ContactEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
                statement.mo7549bindText(2, entity.getName());
                statement.mo7549bindText(3, ContactDao_Impl.this.__converters.fromContactType(entity.getType()));
            }
        };
        this.__deleteAdapterOfContactEntity = new EntityDeleteOrUpdateAdapter<ContactEntity>() { // from class: com.mohammedalhzmi.masrofmanager.data.ContactDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `contacts` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, ContactEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
            }
        };
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.ContactDao
    public Object insertContact(final ContactEntity contactEntity, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.ContactDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ContactDao_Impl.insertContact$lambda$0(this.f$0, contactEntity, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit insertContact$lambda$0(ContactDao_Impl contactDao_Impl, ContactEntity contactEntity, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        contactDao_Impl.__insertAdapterOfContactEntity.insert(_connection, contactEntity);
        return Unit.INSTANCE;
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.ContactDao
    public Object deleteContact(final ContactEntity contactEntity, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.ContactDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ContactDao_Impl.deleteContact$lambda$1(this.f$0, contactEntity, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deleteContact$lambda$1(ContactDao_Impl contactDao_Impl, ContactEntity contactEntity, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        contactDao_Impl.__deleteAdapterOfContactEntity.handle(_connection, contactEntity);
        return Unit.INSTANCE;
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.ContactDao
    public Flow<List<ContactEntity>> getAllContacts() {
        final String str = "SELECT * FROM contacts ORDER BY name ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"contacts"}, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.ContactDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ContactDao_Impl.getAllContacts$lambda$2(str, this, (SQLiteConnection) obj);
            }
        });
    }

    static final List getAllContacts$lambda$2(String str, ContactDao_Impl contactDao_Impl, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "name");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, LinkHeader.Parameters.Type);
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                arrayList.add(new ContactEntity(sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.getText(columnIndexOrThrow2), contactDao_Impl.__converters.toContactType(sQLiteStatementPrepare.getText(columnIndexOrThrow3))));
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th) {
            sQLiteStatementPrepare.close();
            throw th;
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.ContactDao
    public Flow<List<ContactEntity>> getContactsByType(final ContactType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        final String str = "SELECT * FROM contacts WHERE type = ? ORDER BY name ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"contacts"}, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.ContactDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ContactDao_Impl.getContactsByType$lambda$3(str, this, type, (SQLiteConnection) obj);
            }
        });
    }

    static final List getContactsByType$lambda$3(String str, ContactDao_Impl contactDao_Impl, ContactType contactType, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo7549bindText(1, contactDao_Impl.__converters.fromContactType(contactType));
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "name");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, LinkHeader.Parameters.Type);
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                arrayList.add(new ContactEntity(sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.getText(columnIndexOrThrow2), contactDao_Impl.__converters.toContactType(sQLiteStatementPrepare.getText(columnIndexOrThrow3))));
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th) {
            sQLiteStatementPrepare.close();
            throw th;
        }
    }

    /* JADX INFO: compiled from: ContactDao_Impl.kt */
    @Metadata(m913d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/ContactDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
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
