package com.plensee

import com.mygdx.game.voxel.reader.BinaryReader
import com.mygdx.game.voxel.reader.BinaryWriter
import com.mygdx.game.voxel.reader.VoxIO
import java.io.*
import java.nio.file.DirectoryStream
import java.nio.file.Path


object Main {

    @JvmStatic fun main(args: Array<String>) {
        // write your code here
        print("starting")
        val file = File(".")
        val ftw = file.walk().maxDepth(1)
        ftw.forEach { f ->
            if (f.isFile && f.extension == ("vox")) {
                println("processing ${f.path}")
                val dis = DataInputStream(FileInputStream(f))
                val fileSize=f.length().toInt()
                val voxData=VoxIO.fromMagica(BinaryReader(fileSize,dis),null)
                dis.close()
                if(voxData!=null) {
                    val of = File(f.path) //overwrite original files
                    val ofs = FileOutputStream(of, false)
                    val dos = DataOutputStream(ofs)
                    VoxIO.toMagica(BinaryWriter(dos),voxData) //write
                    ofs.close()
                }
            }

        }
    }
}
