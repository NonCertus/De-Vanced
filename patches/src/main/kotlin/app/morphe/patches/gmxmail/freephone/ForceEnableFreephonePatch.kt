/*
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/gmxmail/freephone/ForceEnableFreephonePatch.kt
 */
package app.morphe.patches.gmxmail.freephone

import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.util.returnEarly
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction

@Suppress("unused")
val forceEnableFreePhonePatch = bytecodePatch(
    name = "Force enable FreePhone",
    description = "Enables the FreePhone menu in the navigation drawer even on devices that do not support eSIM.",
) {
    compatibleWith(AppCompatibilities.GMX_MAIL)

    execute {
        IsEuiccEnabledFingerprint.method.returnEarly(true)

        IsFeatureEnabledFingerprint.method.addInstructions(
            0,
            """
                 sget-object v0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;
                 return-object v0
             """.trimIndent(),
        )

        HasEsimSupportFingerprint.method.addInstructions(
            0,
            """
                 sget-object v0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;
                 return-object v0
             """.trimIndent(),
        )

        EsimEligibilityResultFingerprint.let {
            val moveResult = it.instructionMatches[2]
            val register = moveResult
                .getInstruction<OneRegisterInstruction>()
                .registerA

            it.method.addInstructions(
                moveResult.index + 1,
                "const/4 v$register, 0x1",
            )
        }

        EsimUrlIfSupportedAndPremiumFingerprint.method.addInstructions(
            EsimUrlIfSupportedAndPremiumFingerprint.instructionMatches[1].index + 1,
            """
                 const-string v0, "GMX_ESIM_URL"
                 invoke-static {v0, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
             """.trimIndent(),
        )

        EsimUrlIfSupportedFingerprint.method.addInstructions(
            EsimUrlIfSupportedFingerprint.instructionMatches[1].index + 1,
            """
                 const-string v0, "GMX_ESIM_URL"
                 invoke-static {v0, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
             """.trimIndent(),
        )

        EsimUrlIfNotSupportedFingerprint.method.addInstructions(
            EsimUrlIfNotSupportedFingerprint.instructionMatches[1].index + 1,
            """
                 const-string v0, "GMX_ESIM_URL"
                 invoke-static {v0, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
             """.trimIndent(),
        )
    }
}