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
        }
    }
