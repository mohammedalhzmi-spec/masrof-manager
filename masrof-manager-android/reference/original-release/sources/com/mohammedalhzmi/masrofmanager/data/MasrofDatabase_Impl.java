package com.mohammedalhzmi.masrofmanager.data;

import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.room.InvalidationTracker;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: MasrofDatabase_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\"\u0010\u0017\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0019\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u001a0\u0018H\u0014J\u0016\u0010\u001b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u001d0\u00190\u001cH\u0016J*\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001a2\u001a\u0010 \u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u001d0\u0019\u0012\u0004\u0012\u00020\u001d0\u0018H\u0016J\b\u0010!\u001a\u00020\u0006H\u0016J\b\u0010\"\u001a\u00020\bH\u0016J\b\u0010#\u001a\u00020\nH\u0016J\b\u0010$\u001a\u00020\fH\u0016J\b\u0010%\u001a\u00020\u000eH\u0016J\b\u0010&\u001a\u00020\u0010H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/MasrofDatabase_Impl;", "Lcom/mohammedalhzmi/masrofmanager/data/MasrofDatabase;", "<init>", "()V", "_documentDao", "Lkotlin/Lazy;", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDao;", "_settingsDao", "Lcom/mohammedalhzmi/masrofmanager/data/SettingsDao;", "_contactDao", "Lcom/mohammedalhzmi/masrofmanager/data/ContactDao;", "_userDao", "Lcom/mohammedalhzmi/masrofmanager/data/UserDao;", "_auditDao", "Lcom/mohammedalhzmi/masrofmanager/data/AuditDao;", "_designDao", "Lcom/mohammedalhzmi/masrofmanager/data/DesignDao;", "createOpenDelegate", "Landroidx/room/RoomOpenDelegate;", "createInvalidationTracker", "Landroidx/room/InvalidationTracker;", "clearAllTables", "", "getRequiredTypeConverterClasses", "", "Lkotlin/reflect/KClass;", "", "getRequiredAutoMigrationSpecClasses", "", "Landroidx/room/migration/AutoMigrationSpec;", "createAutoMigrations", "Landroidx/room/migration/Migration;", "autoMigrationSpecs", "documentDao", "settingsDao", "contactDao", "userDao", "auditDao", "designDao", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class MasrofDatabase_Impl extends MasrofDatabase {
    public static final int $stable = 8;
    private final Lazy<DocumentDao> _documentDao = LazyKt.lazy(new Function0() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase_Impl$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return MasrofDatabase_Impl._documentDao$lambda$0(this.f$0);
        }
    });
    private final Lazy<SettingsDao> _settingsDao = LazyKt.lazy(new Function0() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase_Impl$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return MasrofDatabase_Impl._settingsDao$lambda$1(this.f$0);
        }
    });
    private final Lazy<ContactDao> _contactDao = LazyKt.lazy(new Function0() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase_Impl$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return MasrofDatabase_Impl._contactDao$lambda$2(this.f$0);
        }
    });
    private final Lazy<UserDao> _userDao = LazyKt.lazy(new Function0() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase_Impl$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return MasrofDatabase_Impl._userDao$lambda$3(this.f$0);
        }
    });
    private final Lazy<AuditDao> _auditDao = LazyKt.lazy(new Function0() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase_Impl$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return MasrofDatabase_Impl._auditDao$lambda$4(this.f$0);
        }
    });
    private final Lazy<DesignDao> _designDao = LazyKt.lazy(new Function0() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase_Impl$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return MasrofDatabase_Impl._designDao$lambda$5(this.f$0);
        }
    });

    static final DocumentDao_Impl _documentDao$lambda$0(MasrofDatabase_Impl masrofDatabase_Impl) {
        return new DocumentDao_Impl(masrofDatabase_Impl);
    }

    static final SettingsDao_Impl _settingsDao$lambda$1(MasrofDatabase_Impl masrofDatabase_Impl) {
        return new SettingsDao_Impl(masrofDatabase_Impl);
    }

    static final ContactDao_Impl _contactDao$lambda$2(MasrofDatabase_Impl masrofDatabase_Impl) {
        return new ContactDao_Impl(masrofDatabase_Impl);
    }

    static final UserDao_Impl _userDao$lambda$3(MasrofDatabase_Impl masrofDatabase_Impl) {
        return new UserDao_Impl(masrofDatabase_Impl);
    }

    static final AuditDao_Impl _auditDao$lambda$4(MasrofDatabase_Impl masrofDatabase_Impl) {
        return new AuditDao_Impl(masrofDatabase_Impl);
    }

    static final DesignDao_Impl _designDao$lambda$5(MasrofDatabase_Impl masrofDatabase_Impl) {
        return new DesignDao_Impl(masrofDatabase_Impl);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.room.RoomDatabase
    public RoomOpenDelegate createOpenDelegate() {
        return new RoomOpenDelegate() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase_Impl$createOpenDelegate$_openDelegate$1
            @Override // androidx.room.RoomOpenDelegate
            public void onCreate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPostMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            {
                super(11, "631091dd2768b4f4ac953e32e4114639", "16c09be988643ed2903fb471a51ac1dc");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void createAllTables(SQLiteConnection connection) throws Exception {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `documents` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `type` TEXT NOT NULL, `documentNumber` TEXT NOT NULL, `dateHijri` TEXT NOT NULL, `dateGregorian` TEXT NOT NULL, `amount` REAL, `amountWords` TEXT, `beneficiaryName` TEXT, `purpose` TEXT, `details` TEXT, `notes` TEXT, `status` TEXT NOT NULL, `attachmentsCount` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `isArchived` INTEGER NOT NULL, `archivedAt` INTEGER, `updatedAt` INTEGER NOT NULL, `tags` TEXT NOT NULL, `financialCategory` TEXT NOT NULL, `costCenter` TEXT NOT NULL, `fundingSource` TEXT NOT NULL, `beneficiaryId` TEXT NOT NULL, `submittedBy` TEXT NOT NULL, `reviewedBy` TEXT NOT NULL, `approvedBy` TEXT NOT NULL, `approvedAt` INTEGER, `paidAt` INTEGER, `rejectionReason` TEXT NOT NULL, `cloudId` TEXT NOT NULL)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `organization_profile` (`id` INTEGER NOT NULL, `ministryName` TEXT NOT NULL, `administrationName` TEXT NOT NULL, `branchName` TEXT NOT NULL, `address` TEXT NOT NULL, `phone` TEXT NOT NULL, `logoPath` TEXT, `managerName` TEXT NOT NULL, `financeManagerName` TEXT NOT NULL, `auditorName` TEXT NOT NULL, `treasurerName` TEXT NOT NULL, `signatureManagerPath` TEXT, `signatureFinancePath` TEXT, `signatureTreasurerPath` TEXT, PRIMARY KEY(`id`))");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `contacts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT NOT NULL, `passwordHash` TEXT NOT NULL, `fullName` TEXT NOT NULL, `role` TEXT NOT NULL, `active` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_users_username` ON `users` (`username`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `audit_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER, `username` TEXT NOT NULL, `action` TEXT NOT NULL, `details` TEXT NOT NULL, `timestamp` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `document_designs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `documentType` TEXT NOT NULL, `name` TEXT NOT NULL, `pageWidth` REAL NOT NULL, `pageHeight` REAL NOT NULL, `backgroundColor` TEXT NOT NULL, `backgroundImageUri` TEXT, `updatedAt` INTEGER NOT NULL, `orientation` TEXT NOT NULL, `marginLeft` REAL NOT NULL, `marginTop` REAL NOT NULL, `marginRight` REAL NOT NULL, `marginBottom` REAL NOT NULL)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_document_designs_documentType` ON `document_designs` (`documentType`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `design_elements` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `designId` INTEGER NOT NULL, `type` TEXT NOT NULL, `content` TEXT NOT NULL, `x` REAL NOT NULL, `y` REAL NOT NULL, `width` REAL NOT NULL, `height` REAL NOT NULL, `rotation` REAL NOT NULL, `opacity` REAL NOT NULL, `zIndex` INTEGER NOT NULL, `fontFamily` TEXT NOT NULL, `fontSize` REAL NOT NULL, `textColor` TEXT NOT NULL, `bold` INTEGER NOT NULL, `italic` INTEGER NOT NULL, `underline` INTEGER NOT NULL, `fillColor` TEXT NOT NULL, `strokeColor` TEXT NOT NULL, `strokeWidth` REAL NOT NULL, `locked` INTEGER NOT NULL, `visible` INTEGER NOT NULL, `textAlign` TEXT NOT NULL, `lineSpacing` REAL NOT NULL, `cornerRadius` REAL NOT NULL)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_design_elements_designId` ON `design_elements` (`designId`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_design_elements_designId_zIndex` ON `design_elements` (`designId`, `zIndex`)");
                SQLite.execSQL(connection, RoomMasterTable.CREATE_QUERY);
                SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '631091dd2768b4f4ac953e32e4114639')");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void dropAllTables(SQLiteConnection connection) throws Exception {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `documents`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `organization_profile`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `contacts`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `users`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `audit_logs`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `document_designs`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `design_elements`");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onOpen(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                this.this$0.internalInitInvalidationTracker(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPreMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                DBUtil.dropFtsSyncTriggers(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                linkedHashMap.put(LinkHeader.Parameters.Type, new TableInfo.Column(LinkHeader.Parameters.Type, "TEXT", true, 0, null, 1));
                linkedHashMap.put("documentNumber", new TableInfo.Column("documentNumber", "TEXT", true, 0, null, 1));
                linkedHashMap.put("dateHijri", new TableInfo.Column("dateHijri", "TEXT", true, 0, null, 1));
                linkedHashMap.put("dateGregorian", new TableInfo.Column("dateGregorian", "TEXT", true, 0, null, 1));
                linkedHashMap.put("amount", new TableInfo.Column("amount", "REAL", false, 0, null, 1));
                linkedHashMap.put("amountWords", new TableInfo.Column("amountWords", "TEXT", false, 0, null, 1));
                linkedHashMap.put("beneficiaryName", new TableInfo.Column("beneficiaryName", "TEXT", false, 0, null, 1));
                linkedHashMap.put("purpose", new TableInfo.Column("purpose", "TEXT", false, 0, null, 1));
                linkedHashMap.put("details", new TableInfo.Column("details", "TEXT", false, 0, null, 1));
                linkedHashMap.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, 1));
                linkedHashMap.put(NotificationCompat.CATEGORY_STATUS, new TableInfo.Column(NotificationCompat.CATEGORY_STATUS, "TEXT", true, 0, null, 1));
                linkedHashMap.put("attachmentsCount", new TableInfo.Column("attachmentsCount", "INTEGER", true, 0, null, 1));
                linkedHashMap.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
                linkedHashMap.put("isArchived", new TableInfo.Column("isArchived", "INTEGER", true, 0, null, 1));
                linkedHashMap.put("archivedAt", new TableInfo.Column("archivedAt", "INTEGER", false, 0, null, 1));
                linkedHashMap.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, 1));
                linkedHashMap.put("tags", new TableInfo.Column("tags", "TEXT", true, 0, null, 1));
                linkedHashMap.put("financialCategory", new TableInfo.Column("financialCategory", "TEXT", true, 0, null, 1));
                linkedHashMap.put("costCenter", new TableInfo.Column("costCenter", "TEXT", true, 0, null, 1));
                linkedHashMap.put("fundingSource", new TableInfo.Column("fundingSource", "TEXT", true, 0, null, 1));
                linkedHashMap.put("beneficiaryId", new TableInfo.Column("beneficiaryId", "TEXT", true, 0, null, 1));
                linkedHashMap.put("submittedBy", new TableInfo.Column("submittedBy", "TEXT", true, 0, null, 1));
                linkedHashMap.put("reviewedBy", new TableInfo.Column("reviewedBy", "TEXT", true, 0, null, 1));
                linkedHashMap.put("approvedBy", new TableInfo.Column("approvedBy", "TEXT", true, 0, null, 1));
                linkedHashMap.put("approvedAt", new TableInfo.Column("approvedAt", "INTEGER", false, 0, null, 1));
                linkedHashMap.put("paidAt", new TableInfo.Column("paidAt", "INTEGER", false, 0, null, 1));
                linkedHashMap.put("rejectionReason", new TableInfo.Column("rejectionReason", "TEXT", true, 0, null, 1));
                linkedHashMap.put("cloudId", new TableInfo.Column("cloudId", "TEXT", true, 0, null, 1));
                TableInfo tableInfo = new TableInfo("documents", linkedHashMap, new LinkedHashSet(), new LinkedHashSet());
                TableInfo tableInfo2 = TableInfo.INSTANCE.read(connection, "documents");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenDelegate.ValidationResult(false, "documents(com.mohammedalhzmi.masrofmanager.data.Document).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                linkedHashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                linkedHashMap2.put("ministryName", new TableInfo.Column("ministryName", "TEXT", true, 0, null, 1));
                linkedHashMap2.put("administrationName", new TableInfo.Column("administrationName", "TEXT", true, 0, null, 1));
                linkedHashMap2.put("branchName", new TableInfo.Column("branchName", "TEXT", true, 0, null, 1));
                linkedHashMap2.put("address", new TableInfo.Column("address", "TEXT", true, 0, null, 1));
                linkedHashMap2.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, 1));
                linkedHashMap2.put("logoPath", new TableInfo.Column("logoPath", "TEXT", false, 0, null, 1));
                linkedHashMap2.put("managerName", new TableInfo.Column("managerName", "TEXT", true, 0, null, 1));
                linkedHashMap2.put("financeManagerName", new TableInfo.Column("financeManagerName", "TEXT", true, 0, null, 1));
                linkedHashMap2.put("auditorName", new TableInfo.Column("auditorName", "TEXT", true, 0, null, 1));
                linkedHashMap2.put("treasurerName", new TableInfo.Column("treasurerName", "TEXT", true, 0, null, 1));
                linkedHashMap2.put("signatureManagerPath", new TableInfo.Column("signatureManagerPath", "TEXT", false, 0, null, 1));
                linkedHashMap2.put("signatureFinancePath", new TableInfo.Column("signatureFinancePath", "TEXT", false, 0, null, 1));
                linkedHashMap2.put("signatureTreasurerPath", new TableInfo.Column("signatureTreasurerPath", "TEXT", false, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("organization_profile", linkedHashMap2, new LinkedHashSet(), new LinkedHashSet());
                TableInfo tableInfo4 = TableInfo.INSTANCE.read(connection, "organization_profile");
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenDelegate.ValidationResult(false, "organization_profile(com.mohammedalhzmi.masrofmanager.data.OrganizationProfile).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                linkedHashMap3.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                linkedHashMap3.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, 1));
                linkedHashMap3.put(LinkHeader.Parameters.Type, new TableInfo.Column(LinkHeader.Parameters.Type, "TEXT", true, 0, null, 1));
                TableInfo tableInfo5 = new TableInfo("contacts", linkedHashMap3, new LinkedHashSet(), new LinkedHashSet());
                TableInfo tableInfo6 = TableInfo.INSTANCE.read(connection, "contacts");
                if (!tableInfo5.equals(tableInfo6)) {
                    return new RoomOpenDelegate.ValidationResult(false, "contacts(com.mohammedalhzmi.masrofmanager.data.ContactEntity).\n Expected:\n" + tableInfo5 + "\n Found:\n" + tableInfo6);
                }
                LinkedHashMap linkedHashMap4 = new LinkedHashMap();
                linkedHashMap4.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                linkedHashMap4.put(HintConstants.AUTOFILL_HINT_USERNAME, new TableInfo.Column(HintConstants.AUTOFILL_HINT_USERNAME, "TEXT", true, 0, null, 1));
                linkedHashMap4.put("passwordHash", new TableInfo.Column("passwordHash", "TEXT", true, 0, null, 1));
                linkedHashMap4.put("fullName", new TableInfo.Column("fullName", "TEXT", true, 0, null, 1));
                linkedHashMap4.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, 1));
                linkedHashMap4.put("active", new TableInfo.Column("active", "INTEGER", true, 0, null, 1));
                linkedHashMap4.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                linkedHashSet2.add(new TableInfo.Index("index_users_username", true, CollectionsKt.listOf(HintConstants.AUTOFILL_HINT_USERNAME), CollectionsKt.listOf("ASC")));
                TableInfo tableInfo7 = new TableInfo("users", linkedHashMap4, linkedHashSet, linkedHashSet2);
                TableInfo tableInfo8 = TableInfo.INSTANCE.read(connection, "users");
                if (!tableInfo7.equals(tableInfo8)) {
                    return new RoomOpenDelegate.ValidationResult(false, "users(com.mohammedalhzmi.masrofmanager.data.UserEntity).\n Expected:\n" + tableInfo7 + "\n Found:\n" + tableInfo8);
                }
                LinkedHashMap linkedHashMap5 = new LinkedHashMap();
                linkedHashMap5.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                linkedHashMap5.put("userId", new TableInfo.Column("userId", "INTEGER", false, 0, null, 1));
                linkedHashMap5.put(HintConstants.AUTOFILL_HINT_USERNAME, new TableInfo.Column(HintConstants.AUTOFILL_HINT_USERNAME, "TEXT", true, 0, null, 1));
                linkedHashMap5.put("action", new TableInfo.Column("action", "TEXT", true, 0, null, 1));
                linkedHashMap5.put("details", new TableInfo.Column("details", "TEXT", true, 0, null, 1));
                linkedHashMap5.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo9 = new TableInfo("audit_logs", linkedHashMap5, new LinkedHashSet(), new LinkedHashSet());
                TableInfo tableInfo10 = TableInfo.INSTANCE.read(connection, "audit_logs");
                if (!tableInfo9.equals(tableInfo10)) {
                    return new RoomOpenDelegate.ValidationResult(false, "audit_logs(com.mohammedalhzmi.masrofmanager.data.AuditLogEntity).\n Expected:\n" + tableInfo9 + "\n Found:\n" + tableInfo10);
                }
                LinkedHashMap linkedHashMap6 = new LinkedHashMap();
                linkedHashMap6.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                linkedHashMap6.put("documentType", new TableInfo.Column("documentType", "TEXT", true, 0, null, 1));
                linkedHashMap6.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, 1));
                linkedHashMap6.put("pageWidth", new TableInfo.Column("pageWidth", "REAL", true, 0, null, 1));
                linkedHashMap6.put("pageHeight", new TableInfo.Column("pageHeight", "REAL", true, 0, null, 1));
                linkedHashMap6.put("backgroundColor", new TableInfo.Column("backgroundColor", "TEXT", true, 0, null, 1));
                linkedHashMap6.put("backgroundImageUri", new TableInfo.Column("backgroundImageUri", "TEXT", false, 0, null, 1));
                linkedHashMap6.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, 1));
                linkedHashMap6.put("orientation", new TableInfo.Column("orientation", "TEXT", true, 0, null, 1));
                linkedHashMap6.put("marginLeft", new TableInfo.Column("marginLeft", "REAL", true, 0, null, 1));
                linkedHashMap6.put("marginTop", new TableInfo.Column("marginTop", "REAL", true, 0, null, 1));
                linkedHashMap6.put("marginRight", new TableInfo.Column("marginRight", "REAL", true, 0, null, 1));
                linkedHashMap6.put("marginBottom", new TableInfo.Column("marginBottom", "REAL", true, 0, null, 1));
                LinkedHashSet linkedHashSet3 = new LinkedHashSet();
                LinkedHashSet linkedHashSet4 = new LinkedHashSet();
                linkedHashSet4.add(new TableInfo.Index("index_document_designs_documentType", true, CollectionsKt.listOf("documentType"), CollectionsKt.listOf("ASC")));
                TableInfo tableInfo11 = new TableInfo("document_designs", linkedHashMap6, linkedHashSet3, linkedHashSet4);
                TableInfo tableInfo12 = TableInfo.INSTANCE.read(connection, "document_designs");
                if (!tableInfo11.equals(tableInfo12)) {
                    return new RoomOpenDelegate.ValidationResult(false, "document_designs(com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity).\n Expected:\n" + tableInfo11 + "\n Found:\n" + tableInfo12);
                }
                LinkedHashMap linkedHashMap7 = new LinkedHashMap();
                linkedHashMap7.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                linkedHashMap7.put("designId", new TableInfo.Column("designId", "INTEGER", true, 0, null, 1));
                linkedHashMap7.put(LinkHeader.Parameters.Type, new TableInfo.Column(LinkHeader.Parameters.Type, "TEXT", true, 0, null, 1));
                linkedHashMap7.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, 1));
                linkedHashMap7.put("x", new TableInfo.Column("x", "REAL", true, 0, null, 1));
                linkedHashMap7.put("y", new TableInfo.Column("y", "REAL", true, 0, null, 1));
                linkedHashMap7.put("width", new TableInfo.Column("width", "REAL", true, 0, null, 1));
                linkedHashMap7.put("height", new TableInfo.Column("height", "REAL", true, 0, null, 1));
                linkedHashMap7.put("rotation", new TableInfo.Column("rotation", "REAL", true, 0, null, 1));
                linkedHashMap7.put("opacity", new TableInfo.Column("opacity", "REAL", true, 0, null, 1));
                linkedHashMap7.put("zIndex", new TableInfo.Column("zIndex", "INTEGER", true, 0, null, 1));
                linkedHashMap7.put("fontFamily", new TableInfo.Column("fontFamily", "TEXT", true, 0, null, 1));
                linkedHashMap7.put("fontSize", new TableInfo.Column("fontSize", "REAL", true, 0, null, 1));
                linkedHashMap7.put("textColor", new TableInfo.Column("textColor", "TEXT", true, 0, null, 1));
                linkedHashMap7.put("bold", new TableInfo.Column("bold", "INTEGER", true, 0, null, 1));
                linkedHashMap7.put("italic", new TableInfo.Column("italic", "INTEGER", true, 0, null, 1));
                linkedHashMap7.put("underline", new TableInfo.Column("underline", "INTEGER", true, 0, null, 1));
                linkedHashMap7.put("fillColor", new TableInfo.Column("fillColor", "TEXT", true, 0, null, 1));
                linkedHashMap7.put("strokeColor", new TableInfo.Column("strokeColor", "TEXT", true, 0, null, 1));
                linkedHashMap7.put("strokeWidth", new TableInfo.Column("strokeWidth", "REAL", true, 0, null, 1));
                linkedHashMap7.put("locked", new TableInfo.Column("locked", "INTEGER", true, 0, null, 1));
                linkedHashMap7.put("visible", new TableInfo.Column("visible", "INTEGER", true, 0, null, 1));
                linkedHashMap7.put("textAlign", new TableInfo.Column("textAlign", "TEXT", true, 0, null, 1));
                linkedHashMap7.put("lineSpacing", new TableInfo.Column("lineSpacing", "REAL", true, 0, null, 1));
                linkedHashMap7.put("cornerRadius", new TableInfo.Column("cornerRadius", "REAL", true, 0, null, 1));
                LinkedHashSet linkedHashSet5 = new LinkedHashSet();
                LinkedHashSet linkedHashSet6 = new LinkedHashSet();
                linkedHashSet6.add(new TableInfo.Index("index_design_elements_designId", false, CollectionsKt.listOf("designId"), CollectionsKt.listOf("ASC")));
                linkedHashSet6.add(new TableInfo.Index("index_design_elements_designId_zIndex", false, CollectionsKt.listOf((Object[]) new String[]{"designId", "zIndex"}), CollectionsKt.listOf((Object[]) new String[]{"ASC", "ASC"})));
                TableInfo tableInfo13 = new TableInfo("design_elements", linkedHashMap7, linkedHashSet5, linkedHashSet6);
                TableInfo tableInfo14 = TableInfo.INSTANCE.read(connection, "design_elements");
                if (!tableInfo13.equals(tableInfo14)) {
                    return new RoomOpenDelegate.ValidationResult(false, "design_elements(com.mohammedalhzmi.masrofmanager.data.DesignElementEntity).\n Expected:\n" + tableInfo13 + "\n Found:\n" + tableInfo14);
                }
                return new RoomOpenDelegate.ValidationResult(true, null);
            }
        };
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new LinkedHashMap(), new LinkedHashMap(), "documents", "organization_profile", "contacts", "users", "audit_logs", "document_designs", "design_elements");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.performClear(false, "documents", "organization_profile", "contacts", "users", "audit_logs", "document_designs", "design_elements");
    }

    @Override // androidx.room.RoomDatabase
    protected Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClasses() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Reflection.getOrCreateKotlinClass(DocumentDao.class), DocumentDao_Impl.INSTANCE.getRequiredConverters());
        linkedHashMap.put(Reflection.getOrCreateKotlinClass(SettingsDao.class), SettingsDao_Impl.INSTANCE.getRequiredConverters());
        linkedHashMap.put(Reflection.getOrCreateKotlinClass(ContactDao.class), ContactDao_Impl.INSTANCE.getRequiredConverters());
        linkedHashMap.put(Reflection.getOrCreateKotlinClass(UserDao.class), UserDao_Impl.INSTANCE.getRequiredConverters());
        linkedHashMap.put(Reflection.getOrCreateKotlinClass(AuditDao.class), AuditDao_Impl.INSTANCE.getRequiredConverters());
        linkedHashMap.put(Reflection.getOrCreateKotlinClass(DesignDao.class), DesignDao_Impl.INSTANCE.getRequiredConverters());
        return linkedHashMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<KClass<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecClasses() {
        return new LinkedHashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> createAutoMigrations(Map<KClass<? extends AutoMigrationSpec>, ? extends AutoMigrationSpec> autoMigrationSpecs) {
        Intrinsics.checkNotNullParameter(autoMigrationSpecs, "autoMigrationSpecs");
        return new ArrayList();
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.MasrofDatabase
    public DocumentDao documentDao() {
        return this._documentDao.getValue();
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.MasrofDatabase
    public SettingsDao settingsDao() {
        return this._settingsDao.getValue();
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.MasrofDatabase
    public ContactDao contactDao() {
        return this._contactDao.getValue();
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.MasrofDatabase
    public UserDao userDao() {
        return this._userDao.getValue();
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.MasrofDatabase
    public AuditDao auditDao() {
        return this._auditDao.getValue();
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.MasrofDatabase
    public DesignDao designDao() {
        return this._designDao.getValue();
    }
}
