package com.mohammedalhzmi.masrofmanager.data;

import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.google.android.gms.common.Scopes;
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

/* JADX INFO: compiled from: SettingsDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/SettingsDao_Impl;", "Lcom/mohammedalhzmi/masrofmanager/data/SettingsDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfOrganizationProfile", "Landroidx/room/EntityInsertAdapter;", "Lcom/mohammedalhzmi/masrofmanager/data/OrganizationProfile;", "saveOrganizationProfile", "", Scopes.PROFILE, "(Lcom/mohammedalhzmi/masrofmanager/data/OrganizationProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrganizationProfile", "Lkotlinx/coroutines/flow/Flow;", "Companion", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class SettingsDao_Impl implements SettingsDao {
    private final RoomDatabase __db;
    private final EntityInsertAdapter<OrganizationProfile> __insertAdapterOfOrganizationProfile;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public SettingsDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfOrganizationProfile = new EntityInsertAdapter<OrganizationProfile>() { // from class: com.mohammedalhzmi.masrofmanager.data.SettingsDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `organization_profile` (`id`,`ministryName`,`administrationName`,`branchName`,`address`,`phone`,`logoPath`,`managerName`,`financeManagerName`,`auditorName`,`treasurerName`,`signatureManagerPath`,`signatureFinancePath`,`signatureTreasurerPath`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, OrganizationProfile entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
                statement.mo7549bindText(2, entity.getMinistryName());
                statement.mo7549bindText(3, entity.getAdministrationName());
                statement.mo7549bindText(4, entity.getBranchName());
                statement.mo7549bindText(5, entity.getAddress());
                statement.mo7549bindText(6, entity.getPhone());
                String logoPath = entity.getLogoPath();
                if (logoPath == null) {
                    statement.mo7548bindNull(7);
                } else {
                    statement.mo7549bindText(7, logoPath);
                }
                statement.mo7549bindText(8, entity.getManagerName());
                statement.mo7549bindText(9, entity.getFinanceManagerName());
                statement.mo7549bindText(10, entity.getAuditorName());
                statement.mo7549bindText(11, entity.getTreasurerName());
                String signatureManagerPath = entity.getSignatureManagerPath();
                if (signatureManagerPath == null) {
                    statement.mo7548bindNull(12);
                } else {
                    statement.mo7549bindText(12, signatureManagerPath);
                }
                String signatureFinancePath = entity.getSignatureFinancePath();
                if (signatureFinancePath == null) {
                    statement.mo7548bindNull(13);
                } else {
                    statement.mo7549bindText(13, signatureFinancePath);
                }
                String signatureTreasurerPath = entity.getSignatureTreasurerPath();
                if (signatureTreasurerPath == null) {
                    statement.mo7548bindNull(14);
                } else {
                    statement.mo7549bindText(14, signatureTreasurerPath);
                }
            }
        };
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.SettingsDao
    public Object saveOrganizationProfile(final OrganizationProfile organizationProfile, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.SettingsDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsDao_Impl.saveOrganizationProfile$lambda$0(this.f$0, organizationProfile, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit saveOrganizationProfile$lambda$0(SettingsDao_Impl settingsDao_Impl, OrganizationProfile organizationProfile, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        settingsDao_Impl.__insertAdapterOfOrganizationProfile.insert(_connection, organizationProfile);
        return Unit.INSTANCE;
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.SettingsDao
    public Flow<OrganizationProfile> getOrganizationProfile() {
        final String str = "SELECT * FROM organization_profile WHERE id = 1";
        return FlowUtil.createFlow(this.__db, false, new String[]{"organization_profile"}, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.SettingsDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsDao_Impl.getOrganizationProfile$lambda$1(str, (SQLiteConnection) obj);
            }
        });
    }

    static final OrganizationProfile getOrganizationProfile$lambda$1(String str, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "ministryName");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "administrationName");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "branchName");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "address");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "phone");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "logoPath");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "managerName");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "financeManagerName");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "auditorName");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "treasurerName");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "signatureManagerPath");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "signatureFinancePath");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "signatureTreasurerPath");
            OrganizationProfile organizationProfile = null;
            if (sQLiteStatementPrepare.step()) {
                organizationProfile = new OrganizationProfile((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.getText(columnIndexOrThrow2), sQLiteStatementPrepare.getText(columnIndexOrThrow3), sQLiteStatementPrepare.getText(columnIndexOrThrow4), sQLiteStatementPrepare.getText(columnIndexOrThrow5), sQLiteStatementPrepare.getText(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.getText(columnIndexOrThrow8), sQLiteStatementPrepare.getText(columnIndexOrThrow9), sQLiteStatementPrepare.getText(columnIndexOrThrow10), sQLiteStatementPrepare.getText(columnIndexOrThrow11), sQLiteStatementPrepare.isNull(columnIndexOrThrow12) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow12), sQLiteStatementPrepare.isNull(columnIndexOrThrow13) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow13), sQLiteStatementPrepare.isNull(columnIndexOrThrow14) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow14));
            }
            return organizationProfile;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    /* JADX INFO: compiled from: SettingsDao_Impl.kt */
    @Metadata(m913d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/SettingsDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
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
