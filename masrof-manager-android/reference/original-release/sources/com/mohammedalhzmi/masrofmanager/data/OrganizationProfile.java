package com.mohammedalhzmi.masrofmanager.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b.\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u009d\u0001\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u00020\u0003HÖ\u0001J\t\u00107\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017¨\u00068"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/OrganizationProfile;", "", "id", "", "ministryName", "", "administrationName", "branchName", "address", "phone", "logoPath", "managerName", "financeManagerName", "auditorName", "treasurerName", "signatureManagerPath", "signatureFinancePath", "signatureTreasurerPath", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getMinistryName", "()Ljava/lang/String;", "getAdministrationName", "getBranchName", "getAddress", "getPhone", "getLogoPath", "getManagerName", "getFinanceManagerName", "getAuditorName", "getTreasurerName", "getSignatureManagerPath", "getSignatureFinancePath", "getSignatureTreasurerPath", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "", "other", "hashCode", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final /* data */ class OrganizationProfile {
    public static final int $stable = 0;
    private final String address;
    private final String administrationName;
    private final String auditorName;
    private final String branchName;
    private final String financeManagerName;
    private final int id;
    private final String logoPath;
    private final String managerName;
    private final String ministryName;
    private final String phone;
    private final String signatureFinancePath;
    private final String signatureManagerPath;
    private final String signatureTreasurerPath;
    private final String treasurerName;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getAuditorName() {
        return this.auditorName;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getTreasurerName() {
        return this.treasurerName;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getSignatureManagerPath() {
        return this.signatureManagerPath;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getSignatureFinancePath() {
        return this.signatureFinancePath;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getSignatureTreasurerPath() {
        return this.signatureTreasurerPath;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMinistryName() {
        return this.ministryName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAdministrationName() {
        return this.administrationName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getBranchName() {
        return this.branchName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getLogoPath() {
        return this.logoPath;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getManagerName() {
        return this.managerName;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getFinanceManagerName() {
        return this.financeManagerName;
    }

    public final OrganizationProfile copy(int id, String ministryName, String administrationName, String branchName, String address, String phone, String logoPath, String managerName, String financeManagerName, String auditorName, String treasurerName, String signatureManagerPath, String signatureFinancePath, String signatureTreasurerPath) {
        Intrinsics.checkNotNullParameter(ministryName, "ministryName");
        Intrinsics.checkNotNullParameter(administrationName, "administrationName");
        Intrinsics.checkNotNullParameter(branchName, "branchName");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(phone, "phone");
        Intrinsics.checkNotNullParameter(managerName, "managerName");
        Intrinsics.checkNotNullParameter(financeManagerName, "financeManagerName");
        Intrinsics.checkNotNullParameter(auditorName, "auditorName");
        Intrinsics.checkNotNullParameter(treasurerName, "treasurerName");
        return new OrganizationProfile(id, ministryName, administrationName, branchName, address, phone, logoPath, managerName, financeManagerName, auditorName, treasurerName, signatureManagerPath, signatureFinancePath, signatureTreasurerPath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OrganizationProfile)) {
            return false;
        }
        OrganizationProfile organizationProfile = (OrganizationProfile) other;
        return this.id == organizationProfile.id && Intrinsics.areEqual(this.ministryName, organizationProfile.ministryName) && Intrinsics.areEqual(this.administrationName, organizationProfile.administrationName) && Intrinsics.areEqual(this.branchName, organizationProfile.branchName) && Intrinsics.areEqual(this.address, organizationProfile.address) && Intrinsics.areEqual(this.phone, organizationProfile.phone) && Intrinsics.areEqual(this.logoPath, organizationProfile.logoPath) && Intrinsics.areEqual(this.managerName, organizationProfile.managerName) && Intrinsics.areEqual(this.financeManagerName, organizationProfile.financeManagerName) && Intrinsics.areEqual(this.auditorName, organizationProfile.auditorName) && Intrinsics.areEqual(this.treasurerName, organizationProfile.treasurerName) && Intrinsics.areEqual(this.signatureManagerPath, organizationProfile.signatureManagerPath) && Intrinsics.areEqual(this.signatureFinancePath, organizationProfile.signatureFinancePath) && Intrinsics.areEqual(this.signatureTreasurerPath, organizationProfile.signatureTreasurerPath);
    }

    public int hashCode() {
        int iHashCode = ((((((((((Integer.hashCode(this.id) * 31) + this.ministryName.hashCode()) * 31) + this.administrationName.hashCode()) * 31) + this.branchName.hashCode()) * 31) + this.address.hashCode()) * 31) + this.phone.hashCode()) * 31;
        String str = this.logoPath;
        int iHashCode2 = (((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.managerName.hashCode()) * 31) + this.financeManagerName.hashCode()) * 31) + this.auditorName.hashCode()) * 31) + this.treasurerName.hashCode()) * 31;
        String str2 = this.signatureManagerPath;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.signatureFinancePath;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.signatureTreasurerPath;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "OrganizationProfile(id=" + this.id + ", ministryName=" + this.ministryName + ", administrationName=" + this.administrationName + ", branchName=" + this.branchName + ", address=" + this.address + ", phone=" + this.phone + ", logoPath=" + this.logoPath + ", managerName=" + this.managerName + ", financeManagerName=" + this.financeManagerName + ", auditorName=" + this.auditorName + ", treasurerName=" + this.treasurerName + ", signatureManagerPath=" + this.signatureManagerPath + ", signatureFinancePath=" + this.signatureFinancePath + ", signatureTreasurerPath=" + this.signatureTreasurerPath + ")";
    }

    public OrganizationProfile(int i, String ministryName, String administrationName, String branchName, String address, String phone, String str, String managerName, String financeManagerName, String auditorName, String treasurerName, String str2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(ministryName, "ministryName");
        Intrinsics.checkNotNullParameter(administrationName, "administrationName");
        Intrinsics.checkNotNullParameter(branchName, "branchName");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(phone, "phone");
        Intrinsics.checkNotNullParameter(managerName, "managerName");
        Intrinsics.checkNotNullParameter(financeManagerName, "financeManagerName");
        Intrinsics.checkNotNullParameter(auditorName, "auditorName");
        Intrinsics.checkNotNullParameter(treasurerName, "treasurerName");
        this.id = i;
        this.ministryName = ministryName;
        this.administrationName = administrationName;
        this.branchName = branchName;
        this.address = address;
        this.phone = phone;
        this.logoPath = str;
        this.managerName = managerName;
        this.financeManagerName = financeManagerName;
        this.auditorName = auditorName;
        this.treasurerName = treasurerName;
        this.signatureManagerPath = str2;
        this.signatureFinancePath = str3;
        this.signatureTreasurerPath = str4;
    }

    public /* synthetic */ OrganizationProfile(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 1 : i, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13);
    }

    public final String getAdministrationName() {
        return this.administrationName;
    }

    public final String getBranchName() {
        return this.branchName;
    }

    public final int getId() {
        return this.id;
    }

    public final String getMinistryName() {
        return this.ministryName;
    }

    public final String getAddress() {
        return this.address;
    }

    public final String getFinanceManagerName() {
        return this.financeManagerName;
    }

    public final String getLogoPath() {
        return this.logoPath;
    }

    public final String getManagerName() {
        return this.managerName;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final String getAuditorName() {
        return this.auditorName;
    }

    public final String getSignatureFinancePath() {
        return this.signatureFinancePath;
    }

    public final String getSignatureManagerPath() {
        return this.signatureManagerPath;
    }

    public final String getSignatureTreasurerPath() {
        return this.signatureTreasurerPath;
    }

    public final String getTreasurerName() {
        return this.treasurerName;
    }
}
