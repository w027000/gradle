/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.publish.internal;

import org.gradle.api.Action;
import org.gradle.api.artifacts.ModuleVersionIdentifier;
import org.gradle.api.artifacts.PublishArtifact;
import org.gradle.api.internal.artifacts.ivyservice.projectmodule.ProjectPublication;
import org.gradle.api.internal.attributes.ImmutableAttributes;
import org.gradle.api.publish.Publication;
import org.gradle.api.publish.PublicationArtifact;
import org.gradle.internal.Factory;

import java.io.File;

public interface PublicationInternal<T extends PublicationArtifact> extends Publication, ProjectPublication {
    ModuleVersionIdentifier getCoordinates();

    ImmutableAttributes getAttributes();

    void setAlias(boolean alias);

    /**
     * Returns all publishable artifacts of this publication (read-only).
     */
    PublicationArtifactSet<T> getPublishableArtifacts();

    void allPublishableArtifacts(Action<? super T> action);

    void whenPublishableArtifactRemoved(Action<? super T> action);

    T addDerivedArtifact(T originalArtifact, Factory<File> file);

    void removeDerivedArtifact(T artifact);

    /**
     * Provide the file coordinates for the published artifact, if any.
     *
     * @param source The original PublishArtifact
     * @return The name and URI of the published file, or `null` if the source artifact is not published.
     */
    PublishedFile getPublishedFile(PublishArtifact source);

    interface PublishedFile {
        String getName();

        String getUri();
    }
}
