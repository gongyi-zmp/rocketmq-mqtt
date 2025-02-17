/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.rocketmq.mqtt.exporter;

import io.prometheus.client.hotspot.DefaultExports;
import org.apache.rocketmq.mqtt.exporter.collector.MqttMetricsCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttExporter {
    protected static final Logger LOG = LoggerFactory.getLogger(MqttExporter.class);

    private final String nameSpace;
    private final String hostName;
    private final String hostIp;
    private final int exporterPort;

    public MqttExporter(String nameSpace, String hostName, String hostIp, int exporterPort) {
        this.nameSpace = nameSpace;
        this.hostName = hostName;
        this.hostIp = hostIp;
        this.exporterPort = exporterPort;
    }

    public void start() throws Exception {
        // todo if start jvm exporter default
        DefaultExports.initialize();
        MqttMetricsCollector.initialize(this.nameSpace, this.hostName, this.hostIp, this.exporterPort);
        LOG.info("metrics exporter start success");

    }

    public void shutdown() {
        MqttMetricsCollector.shutdown();
    }
}
