package com.example.unsplashv3.ui.screens.main.info

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.ui.tooling.preview.Preview
import com.example.unsplashv3.R


@Preview(showBackground = true, showDecoration = true)
@Composable
fun InfoScreen() {
    val typography = MaterialTheme.typography
    val context = AmbientContext.current
    ScrollableColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp, 16.dp, 16.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(imageResource(id = R.drawable.my_photo), modifier = Modifier.wrapContentWidth())
        Text(text = stringResource(id = R.string.about), style = typography.h4)
        Text(text = stringResource(id = R.string.libs), style = typography.body1)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(id = R.string.write_to_me), style = typography.body1)
            Spacer(modifier = Modifier.size(16.dp))
            IconButton(onClick = {
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(context.resources.getString(R.string.url_telegram))
                }.let {
                    startActivity(context, it, null)
                }
            }) {
                Icon(vectorResource(id = R.drawable.ic_telegram_logo), tint = Color.Blue)
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text("или", style = typography.body1)
            Spacer(modifier = Modifier.size(16.dp))
            IconButton(onClick = {
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", context.resources.getString(R.string.my_mail), null)).apply {
                    putExtra(Intent.EXTRA_SUBJECT, context.resources.getString(R.string.app_name))
                }.let {
                    context.startActivity(Intent.createChooser(it, null))
                }
            }) {
                Icon(Icons.Filled.Email)
            }
        }

    }
}