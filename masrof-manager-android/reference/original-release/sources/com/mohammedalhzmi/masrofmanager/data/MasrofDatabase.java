package com.mohammedalhzmi.masrofmanager.data;

import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.p003db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MasrofDatabase.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0011"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/MasrofDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "documentDao", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDao;", "settingsDao", "Lcom/mohammedalhzmi/masrofmanager/data/SettingsDao;", "contactDao", "Lcom/mohammedalhzmi/masrofmanager/data/ContactDao;", "userDao", "Lcom/mohammedalhzmi/masrofmanager/data/UserDao;", "auditDao", "Lcom/mohammedalhzmi/masrofmanager/data/AuditDao;", "designDao", "Lcom/mohammedalhzmi/masrofmanager/data/DesignDao;", "Companion", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public abstract class MasrofDatabase extends RoomDatabase {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final Migration MIGRATION_3_4 = new Migration() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase$Companion$MIGRATION_3_4$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, username TEXT NOT NULL, passwordHash TEXT NOT NULL, fullName TEXT NOT NULL, role TEXT NOT NULL, active INTEGER NOT NULL, createdAt INTEGER NOT NULL)");
            db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_users_username ON users(username)");
            db.execSQL("CREATE TABLE IF NOT EXISTS audit_logs (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, userId INTEGER, username TEXT NOT NULL, action TEXT NOT NULL, details TEXT NOT NULL, timestamp INTEGER NOT NULL)");
        }
    };
    private static final Migration MIGRATION_4_5 = new Migration() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase$Companion$MIGRATION_4_5$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("CREATE TABLE IF NOT EXISTS document_designs (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, documentType TEXT NOT NULL, name TEXT NOT NULL, pageWidth REAL NOT NULL, pageHeight REAL NOT NULL, backgroundColor TEXT NOT NULL, backgroundImageUri TEXT, updatedAt INTEGER NOT NULL)");
            db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_document_designs_documentType ON document_designs(documentType)");
            db.execSQL("CREATE TABLE IF NOT EXISTS design_elements (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, designId INTEGER NOT NULL, type TEXT NOT NULL, content TEXT NOT NULL, x REAL NOT NULL, y REAL NOT NULL, width REAL NOT NULL, height REAL NOT NULL, rotation REAL NOT NULL, opacity REAL NOT NULL, zIndex INTEGER NOT NULL, fontFamily TEXT NOT NULL, fontSize REAL NOT NULL, textColor TEXT NOT NULL, bold INTEGER NOT NULL, italic INTEGER NOT NULL, underline INTEGER NOT NULL, fillColor TEXT NOT NULL, strokeColor TEXT NOT NULL, strokeWidth REAL NOT NULL, locked INTEGER NOT NULL, visible INTEGER NOT NULL)");
            db.execSQL("CREATE INDEX IF NOT EXISTS index_design_elements_designId ON design_elements(designId)");
            db.execSQL("CREATE INDEX IF NOT EXISTS index_design_elements_designId_zIndex ON design_elements(designId, zIndex)");
        }
    };
    private static final Migration MIGRATION_5_6 = new Migration() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase$Companion$MIGRATION_5_6$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("ALTER TABLE design_elements ADD COLUMN textAlign TEXT NOT NULL DEFAULT 'START'");
            db.execSQL("ALTER TABLE design_elements ADD COLUMN lineSpacing REAL NOT NULL DEFAULT 1.0");
            db.execSQL("ALTER TABLE design_elements ADD COLUMN cornerRadius REAL NOT NULL DEFAULT 0.0");
        }
    };
    private static final Migration MIGRATION_6_7 = new Migration() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase$Companion$MIGRATION_6_7$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("ALTER TABLE document_designs ADD COLUMN orientation TEXT NOT NULL DEFAULT 'PORTRAIT'");
            db.execSQL("ALTER TABLE document_designs ADD COLUMN marginLeft REAL NOT NULL DEFAULT 25.0");
            db.execSQL("ALTER TABLE document_designs ADD COLUMN marginTop REAL NOT NULL DEFAULT 25.0");
            db.execSQL("ALTER TABLE document_designs ADD COLUMN marginRight REAL NOT NULL DEFAULT 25.0");
            db.execSQL("ALTER TABLE document_designs ADD COLUMN marginBottom REAL NOT NULL DEFAULT 25.0");
        }
    };
    private static final Migration MIGRATION_7_8 = new Migration() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase$Companion$MIGRATION_7_8$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("ALTER TABLE documents ADD COLUMN isArchived INTEGER NOT NULL DEFAULT 0");
            db.execSQL("ALTER TABLE documents ADD COLUMN archivedAt INTEGER");
            db.execSQL("ALTER TABLE documents ADD COLUMN updatedAt INTEGER NOT NULL DEFAULT 0");
            db.execSQL("UPDATE documents SET updatedAt = createdAt WHERE updatedAt = 0");
            db.execSQL("CREATE INDEX IF NOT EXISTS index_documents_isArchived ON documents(isArchived)");
        }
    };
    private static final Migration MIGRATION_8_9 = new Migration() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase$Companion$MIGRATION_8_9$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("ALTER TABLE documents ADD COLUMN tags TEXT NOT NULL DEFAULT ''");
        }
    };
    private static final Migration MIGRATION_9_10 = new Migration() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase$Companion$MIGRATION_9_10$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("ALTER TABLE documents ADD COLUMN financialCategory TEXT NOT NULL DEFAULT ''");
            db.execSQL("ALTER TABLE documents ADD COLUMN costCenter TEXT NOT NULL DEFAULT ''");
            db.execSQL("ALTER TABLE documents ADD COLUMN fundingSource TEXT NOT NULL DEFAULT ''");
            db.execSQL("ALTER TABLE documents ADD COLUMN beneficiaryId TEXT NOT NULL DEFAULT ''");
            db.execSQL("ALTER TABLE documents ADD COLUMN submittedBy TEXT NOT NULL DEFAULT ''");
            db.execSQL("ALTER TABLE documents ADD COLUMN reviewedBy TEXT NOT NULL DEFAULT ''");
            db.execSQL("ALTER TABLE documents ADD COLUMN approvedBy TEXT NOT NULL DEFAULT ''");
            db.execSQL("ALTER TABLE documents ADD COLUMN approvedAt INTEGER");
            db.execSQL("ALTER TABLE documents ADD COLUMN paidAt INTEGER");
            db.execSQL("ALTER TABLE documents ADD COLUMN rejectionReason TEXT NOT NULL DEFAULT ''");
        }
    };
    private static final Migration MIGRATION_10_11 = new Migration() { // from class: com.mohammedalhzmi.masrofmanager.data.MasrofDatabase$Companion$MIGRATION_10_11$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("ALTER TABLE documents ADD COLUMN cloudId TEXT NOT NULL DEFAULT ''");
        }
    };

    public abstract AuditDao auditDao();

    public abstract ContactDao contactDao();

    public abstract DesignDao designDao();

    public abstract DocumentDao documentDao();

    public abstract SettingsDao settingsDao();

    public abstract UserDao userDao();

    /* JADX INFO: compiled from: MasrofDatabase.kt */
    @Metadata(m913d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007¨\u0006\u0016"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/MasrofDatabase$Companion;", "", "<init>", "()V", "MIGRATION_3_4", "Landroidx/room/migration/Migration;", "getMIGRATION_3_4", "()Landroidx/room/migration/Migration;", "MIGRATION_4_5", "getMIGRATION_4_5", "MIGRATION_5_6", "getMIGRATION_5_6", "MIGRATION_6_7", "getMIGRATION_6_7", "MIGRATION_7_8", "getMIGRATION_7_8", "MIGRATION_8_9", "getMIGRATION_8_9", "MIGRATION_9_10", "getMIGRATION_9_10", "MIGRATION_10_11", "getMIGRATION_10_11", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Migration getMIGRATION_3_4() {
            return MasrofDatabase.MIGRATION_3_4;
        }

        public final Migration getMIGRATION_4_5() {
            return MasrofDatabase.MIGRATION_4_5;
        }

        public final Migration getMIGRATION_5_6() {
            return MasrofDatabase.MIGRATION_5_6;
        }

        public final Migration getMIGRATION_6_7() {
            return MasrofDatabase.MIGRATION_6_7;
        }

        public final Migration getMIGRATION_7_8() {
            return MasrofDatabase.MIGRATION_7_8;
        }

        public final Migration getMIGRATION_8_9() {
            return MasrofDatabase.MIGRATION_8_9;
        }

        public final Migration getMIGRATION_9_10() {
            return MasrofDatabase.MIGRATION_9_10;
        }

        public final Migration getMIGRATION_10_11() {
            return MasrofDatabase.MIGRATION_10_11;
        }
    }
}
